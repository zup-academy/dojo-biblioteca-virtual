package br.com.zuo.edu.biblioteca.emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    Long countByAtivoIsTrueAndUsuarioId(Long usuarioId);

    List<Emprestimo> findAllByAtivoIsTrueAndUsuarioId(Long usuarioId);

}
