package br.com.zuo.edu.biblioteca.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Integer countByIdAndEmprestimosAtivoIsTrue(Long id);

    Boolean existsByIdAndEmprestimosAtivoIsTrueAndEmprestimosDataEntregaBefore(Long id, LocalDate now);
}
