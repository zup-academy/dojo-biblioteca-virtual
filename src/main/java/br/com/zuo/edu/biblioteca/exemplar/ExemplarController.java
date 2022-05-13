package br.com.zuo.edu.biblioteca.exemplar;

import br.com.zuo.edu.biblioteca.livro.Livro;
import br.com.zuo.edu.biblioteca.livro.LivroController;
import br.com.zuo.edu.biblioteca.livro.LivroRepository;
import br.com.zuo.edu.biblioteca.livro.LivroRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(LivroController.BASE_URI)
public class ExemplarController {

    public final static String BASE_URI = "/exemplares";

    private final ExemplarRepository exemplarRepository;

    private final LivroRepository livroRepository;

    public ExemplarController(ExemplarRepository exemplarRepository, LivroRepository livroRepository) {
        this.exemplarRepository = exemplarRepository;
        this.livroRepository = livroRepository;
    }

    @PostMapping("/{isbn}")
    public ResponseEntity<?> cadastrar(@PathVariable String isbn, @RequestBody @Valid ExemplarRequest exemplarRequest,
                                       UriComponentsBuilder ucb) {
        String novoIsbn = isbn.replaceAll("[^0-9X]", "");

        Livro livro = livroRepository.findByIsbn(novoIsbn).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro não encontrado."
        ));


        Livro livro = livroRepository.save(livroRequest.toModel());

        URI location = ucb.path(BASE_URI + "/{id}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
