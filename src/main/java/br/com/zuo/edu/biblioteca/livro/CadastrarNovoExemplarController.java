package br.com.zuo.edu.biblioteca.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastrarNovoExemplarController {

    private final ExemplarLivroRepository repository;

    public CadastrarNovoExemplarController(ExemplarLivroRepository repository) {
        this.repository = repository;
    }
    @PostMapping("/livros/{isbn}/exemplares")
    public ResponseEntity cadastrarExemplar(@PathVariable String isbn,
                                            @RequestBody @Valid CadastrarNovoExemplarRequest resquet,
                                            UriComponentsBuilder uriComponentsBuilder) {

        ExemplarLivro exemplar = resquet.toModel(new Livro());

        URI location = uriComponentsBuilder.path("/livros/{isbn}/exemplares/{idExemplar}")
                .buildAndExpand(isbn, exemplar.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //Status 201 com header de location com endereco do recurso
    //Status 400 caso tenha qualquer falha de validação com as falhas no corpo da resposta
    //Caso o ISBN passado não exista, deve ser retornar status 404.
}
