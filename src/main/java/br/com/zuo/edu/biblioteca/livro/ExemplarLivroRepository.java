package br.com.zuo.edu.biblioteca.livro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExemplarLivroRepository extends JpaRepository<ExemplarLivro, Long> {
    Optional<ExemplarLivro> findFirstByLivroIdAndReservadoIsFalse(Long idLivro);
}