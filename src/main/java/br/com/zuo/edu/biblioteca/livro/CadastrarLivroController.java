package br.com.zuo.edu.biblioteca.livro;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(CadastrarLivroController.BASE_URI)
public class CadastrarLivroController {

    public final static String BASE_URI = "/livros";

    private final LivroRepository livroRepository;

    public CadastrarLivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid LivroRequest livroRequest,
                                       UriComponentsBuilder ucb) {
        String isbn = livroRequest.getIsbn().replaceAll("[^0-9X]", "");

        if (livroRepository.existsByIsbn(isbn)) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY, "Um livro com o ISBN fornecido já está cadastrado."
            );
        }

        Livro livro = livroRepository.save(livroRequest.toModel());

        URI location = ucb.path(BASE_URI + "/{id}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
