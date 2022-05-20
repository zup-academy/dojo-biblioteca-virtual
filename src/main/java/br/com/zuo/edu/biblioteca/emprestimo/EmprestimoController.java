package br.com.zuo.edu.biblioteca.emprestimo;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid EmprestimoRequest emprestimoRequest,
                                       UriComponentsBuilder ucb) {
        Usuario usuario = usuarioRepository.findById(emprestimoRequest.getUsuarioId())
                                           .orElseThrow(
                                               () -> new ResponseStatusException(
                                                   HttpStatus.NOT_FOUND, "Usuário não encontrado."
                                               )
                                           );

        Exemplar exemplar = exemplarRepository.findFirstByLivroIsbn();

        Emprestimo emprestimo = emprestimoRequest.toModel(exemplar, usuario);
    }

}
