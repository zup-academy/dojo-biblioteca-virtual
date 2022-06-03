package br.com.zuo.edu.biblioteca.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevolverExemplarDeLivroController {

    @PatchMapping("/exemplares/{id}/emprestimos")
    public ResponseEntity devolverExemplar(@PathVariable String isbn
                                           //DevolverExemplarDeLivroRequest
                                           ) {


        return ResponseEntity.ok().build();
    }

}
