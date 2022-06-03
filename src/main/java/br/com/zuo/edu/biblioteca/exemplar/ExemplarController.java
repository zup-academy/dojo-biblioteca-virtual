package br.com.zuo.edu.biblioteca.exemplar;

import java.net.URI;

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

import br.com.zuo.edu.biblioteca.livro.CadastrarLivroController;
import br.com.zuo.edu.biblioteca.livro.Livro;
import br.com.zuo.edu.biblioteca.livro.LivroRepository;

@RestController
@RequestMapping(CadastrarLivroController.BASE_URI + "/{isbn}" + ExemplarController.BASE_URI)
public class ExemplarController {

    public final static String BASE_URI = "/exemplares";

    private final ExemplarRepository exemplarRepository;
    private final LivroRepository livroRepository;

    public ExemplarController(ExemplarRepository exemplarRepository,
                              LivroRepository livroRepository) {
        this.exemplarRepository = exemplarRepository;
        this.livroRepository = livroRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@PathVariable String isbn,
                                       @RequestBody @Valid ExemplarRequest exemplarRequest,
                                       UriComponentsBuilder ucb) {
        String novoIsbn = isbn.replaceAll("[^0-9X]", "");

        Livro livro = livroRepository.findByIsbn(novoIsbn)
                                     .orElseThrow(
                                         () -> new ResponseStatusException(
                                             HttpStatus.NOT_FOUND, "Livro não encontrado."
                                         )
                                     );

        Exemplar exemplar = exemplarRepository.save(exemplarRequest.toModel(livro));

        URI location = ucb.path(CadastrarLivroController.BASE_URI + "/{isbn}" + BASE_URI + "/{id}")
                          .buildAndExpand(novoIsbn, exemplar.getId())
                          .toUri();

        return ResponseEntity.created(location).build();
    }

}
