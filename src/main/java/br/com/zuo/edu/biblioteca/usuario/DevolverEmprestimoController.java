package br.com.zuo.edu.biblioteca.usuario;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zuo.edu.biblioteca.emprestimo.Emprestimo;
import br.com.zuo.edu.biblioteca.emprestimo.EmprestimoRepository;

@RestController
public class DevolverEmprestimoController {

    private final UsuarioRepository usuarioRepository;

    private final EmprestimoRepository emprestimoRepository;

    public DevolverEmprestimoController(UsuarioRepository usuarioRepository,
                                        EmprestimoRepository emprestimoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    @Transactional
    @PatchMapping("/usuarios/{idUsuario}/emprestimos/{idEmprestimo}")
    public ResponseEntity<?> devolver(@PathVariable Long idUsuario,
                                      @PathVariable Long idEmprestimo) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                                           .orElseThrow(
                                               () -> new ResponseStatusException(
                                                   HttpStatus.NOT_FOUND, "Usuário não encontrado."
                                               )
                                           );
        Emprestimo emprestimo = emprestimoRepository.findById(idEmprestimo)
                                                    .orElseThrow(
                                                        () -> new ResponseStatusException(
                                                            HttpStatus.NOT_FOUND,
                                                            "Empréstimo não encontrado."
                                                        )
                                                    );

        if (!emprestimo.getUsuario().equals(usuario)) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY, "Empréstimo não pertence a esse usuário."
            );
        }

        if (!emprestimo.isAtivo()) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY, "Esse empréstimo já foi devolvido"
            );
        }

        emprestimo.devolver();

        return ResponseEntity.noContent().build();
    }

}
