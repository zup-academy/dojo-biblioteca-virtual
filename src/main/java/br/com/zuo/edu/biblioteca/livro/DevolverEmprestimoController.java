package br.com.zuo.edu.biblioteca.livro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DevolverEmprestimoController {

    private final EmprestimoRepository emprestimoRepository;

    public DevolverEmprestimoController(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    @PatchMapping("/usuarios/{idUsuario}/emprestimos/{idEmprestimo}")
    public ResponseEntity devolverExemplar(@PathVariable Long idUsuario,
                                           @PathVariable Long idEmprestimo
                                           ) {
        Emprestimo emprestimo = emprestimoRepository.findById(idEmprestimo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado"));

        if (!emprestimo.getUsuario().getId().equals(idUsuario)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "A devolução deve ser feita pelo usuário que realizou o empréstimo");
        }

        if (!emprestimo.isAtivo()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "O empréstimo já foi devolvido");
        }

        emprestimo.devolver();

        return ResponseEntity.ok().build();
    }

}
