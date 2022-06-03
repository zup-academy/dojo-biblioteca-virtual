package br.com.zuo.edu.biblioteca.emprestimo;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zuo.edu.biblioteca.exemplar.Exemplar;
import br.com.zuo.edu.biblioteca.exemplar.ExemplarRepository;
import br.com.zuo.edu.biblioteca.exemplar.TipoCirculacao;
import br.com.zuo.edu.biblioteca.livro.CadastrarLivroController;
import br.com.zuo.edu.biblioteca.usuario.TipoUsuario;
import br.com.zuo.edu.biblioteca.usuario.Usuario;
import br.com.zuo.edu.biblioteca.usuario.UsuarioRepository;

@RestController
@RequestMapping(CadastrarLivroController.BASE_URI + "/{isbn}"
        + RealizarEmprestimoController.BASE_URI)
public class RealizarEmprestimoController {

    public final static String BASE_URI = "/emprestimos";

    private final ExemplarRepository exemplarRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;

    public RealizarEmprestimoController(ExemplarRepository exemplarRepository,
                                        EmprestimoRepository emprestimoRepository,
                                        UsuarioRepository usuarioRepository) {
        this.exemplarRepository = exemplarRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> emprestar(@RequestBody @Valid EmprestimoRequest emprestimoRequest,
                                       @PathVariable String isbn, UriComponentsBuilder ucb) {
        Long usuarioId = emprestimoRequest.getUsuarioId();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                                           .orElseThrow(
                                               () -> new ResponseStatusException(
                                                   HttpStatus.NOT_FOUND, "Usuário não encontrado."
                                               )
                                           );

        Integer prazoEmDias = emprestimoRequest.getPrazoEmDias();
        Optional<Exemplar> optionalExemplar = null;
        TipoUsuario tipoUsuario = usuario.getTipoUsuario();
        String novoIsbn = isbn.replaceAll("[^0-9X]", "");

        if (emprestimoRepository.countByAtivoIsTrueAndDataEntregaPrevistaIsBeforeAndUsuarioId(
            LocalDateTime.now(), usuarioId
        ) > 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Usuário possui um ou mais empréstimos expirados."
            );
        }

        if (tipoUsuario.equals(TipoUsuario.PADRAO)) {
            if (emprestimoRepository.countByAtivoIsTrueAndUsuarioId(usuarioId) >= 5) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Usuário ultrapassou o limite de empréstimos."
                );
            }

            if (prazoEmDias == null) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "É necessário informar um prazo de devolução."
                );
            }

            optionalExemplar = exemplarRepository.findFirstByDisponivelIsTrueAndTipoCirculacaoAndLivroIsbn(
                TipoCirculacao.LIVRE, novoIsbn
            );
        } else {
            optionalExemplar = exemplarRepository.findFirstByDisponivelIsTrueAndLivroIsbn(novoIsbn);

            if (prazoEmDias == null) {
                prazoEmDias = 60;
            }
        }

        Exemplar exemplar = optionalExemplar.orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro sem exemplar disponível."
            )
        );

        Emprestimo emprestimo = emprestimoRequest.toModel(exemplar, usuario, prazoEmDias);

        emprestimoRepository.save(emprestimo);

        URI location = ucb.path(CadastrarLivroController.BASE_URI + "/{isbn}" + BASE_URI + "/{id}")
                          .buildAndExpand(novoIsbn, emprestimo.getId())
                          .toUri();

        return ResponseEntity.created(location).build();

    }

}
