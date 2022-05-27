package br.com.zuo.edu.biblioteca.emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    Long countByAtivoIsTrueAndUsuarioId(Long usuarioId);

    // TODO: é possível fazer uma consulta no repositório para extrair os empréstimos expirados?
    // Long countByIsExpiradoIsTrueAndUsuario_Id(Long usuarioId);

}
