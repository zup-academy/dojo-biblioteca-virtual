package br.com.zuo.edu.biblioteca.livro;

import br.com.zuo.edu.biblioteca.usuario.Usuario;
import br.com.zuo.edu.biblioteca.usuario.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
public class SolicitarEmprestimoController {

    private final LivroRepository livroRepository;
    private final ExemplarLivroRepository exemplarLivroRepository;
    private final UsuarioRepository usuarioRepository;

    public SolicitarEmprestimoController(LivroRepository livroRepository, ExemplarLivroRepository exemplarLivroRepository, UsuarioRepository usuarioRepository) {
        this.livroRepository = livroRepository;
        this.exemplarLivroRepository = exemplarLivroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/livros/{isbn}/emprestimos")
    public ResponseEntity solicitar(@PathVariable String isbn, @RequestBody @Valid SolicitarEmprestimoRequest request, UriComponentsBuilder uriComponentsBuilder) {
        Livro livro = livroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));

        ExemplarLivro exemplar = exemplarLivroRepository.findFirstByLivroAndReservadoIsFalse(livro.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exemplar do livro não encontrado"));

        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário inexistente"));

        ReservaExemplar reserva = exemplar.solicitarEmprestimo(usuario);

        exemplarLivroRepository.flush();

        uriComponentsBuilder.path("/livros/{isbn}/emprestimos/{id}")
                .buildAndExpand(isbn,reserva)

        return ResponseEntity.created(location).build();
    }

}
