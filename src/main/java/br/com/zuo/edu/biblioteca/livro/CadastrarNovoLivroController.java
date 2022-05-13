package br.com.zuo.edu.biblioteca.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastrarNovoLivroController {

    private final LivroRepository livroRepository;

    public CadastrarNovoLivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @PostMapping("/livros")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastrarNovoLivroRequest request, UriComponentsBuilder uriComponentsBuilder) {
        Livro livro = request.toModel();

        livroRepository.save(livro);

        URI location = uriComponentsBuilder.path("/livros/{id}")
                .buildAndExpand(livro.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
