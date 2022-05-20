package br.com.zuo.edu.biblioteca.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolicitarEmprestimoController {

    @PostMapping("/livros/{id}/emprestimos")
    public ResponseEntity solicitar() {

    }

}
