package br.com.zuo.edu.biblioteca.emprestimo;

import java.net.URI;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.zuo.edu.biblioteca.exemplar.TipoCirculacao;
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
import br.com.zuo.edu.biblioteca.livro.LivroController;
import br.com.zuo.edu.biblioteca.livro.LivroRepository;
import br.com.zuo.edu.biblioteca.usuario.TipoUsuario;
import br.com.zuo.edu.biblioteca.usuario.Usuario;
import br.com.zuo.edu.biblioteca.usuario.UsuarioRepository;

@RestController
@RequestMapping(LivroController.BASE_URI + "/{isbn}" + EmprestimoController.BASE_URI)
public class EmprestimoController {

    public final static String BASE_URI = "/emprestimos";

    private final LivroRepository livroRepository;
    private final ExemplarRepository exemplarRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;

    public EmprestimoController(LivroRepository livroRepository,
                                ExemplarRepository exemplarRepository,
                                EmprestimoRepository emprestimoRepository,
                                UsuarioRepository usuarioRepository) {
        this.livroRepository = livroRepository;
        this.exemplarRepository = exemplarRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid EmprestimoRequest emprestimoRequest,
                                       @PathVariable String isbn, UriComponentsBuilder ucb) {
        Usuario usuario = usuarioRepository.findById(emprestimoRequest.getUsuarioId())
                                           .orElseThrow(
                                               () -> new ResponseStatusException(
                                                   HttpStatus.NOT_FOUND, "Usuário não encontrado."
                                               )
                                           );

        LocalDate dataDevolucao = emprestimoRequest.getDataDevolucao();


        Optional<Exemplar> optionalExemplar = null;
        TipoUsuario tipoUsuario = usuario.getTipoUsuario();
        String novoIsbn = isbn.replaceAll("[^0-9X]", "");

        if (tipoUsuario.equals(TipoUsuario.PADRAO)) {
            if (usuario.getEmprestimos().size() >= 5) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Usuário ultrapassou o limite de empréstimos."
                );
            }
            Long duracao = ChronoUnit.DAYS.between(LocalDate.now(),dataDevolucao);
            if (dataDevolucao != null && duracao <= 60){

            }

            optionalExemplar = exemplarRepository.findFirstByDisponivelIsTrueAndTipoCirculacaoAndLivroIsbn(
                    TipoCirculacao.LIVRE, novoIsbn
            );
        } else {
            optionalExemplar = exemplarRepository.findFirstByDisponivelIsTrueAndLivroIsbn(novoIsbn);
        }

        Exemplar exemplar = optionalExemplar.orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro sem exemplar disponível."
            )
        );

        Emprestimo emprestimo = emprestimoRequest.toModel(exemplar, usuario);

        emprestimoRepository.save(emprestimo);

        URI location = ucb.path(LivroController.BASE_URI + "/{isbn}" + BASE_URI + "/{id}")
                          .buildAndExpand(novoIsbn, emprestimo.getId())
                          .toUri();

        return ResponseEntity.created(location).build();

    }

}
