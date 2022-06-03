package br.com.zuo.edu.biblioteca.emprestimo;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    Long countByAtivoIsTrueAndUsuarioId(Long usuarioId);

    // Emprestimos Expirados
    Long countByAtivoIsTrueAndDataEntregaPrevistaIsBeforeAndUsuarioId(LocalDateTime data,
                                                                      Long usuarioId);

    Boolean existsByIdAndUsuarioId(Long id,Long usuarioId);

}
