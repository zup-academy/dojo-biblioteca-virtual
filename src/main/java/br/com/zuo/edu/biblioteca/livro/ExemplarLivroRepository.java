package br.com.zuo.edu.biblioteca.livro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplarLivroRepository extends JpaRepository<ExemplarLivro, Long> {
}