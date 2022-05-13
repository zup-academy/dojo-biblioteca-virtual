package br.com.zuo.edu.biblioteca.exemplar;

import br.com.zuo.edu.biblioteca.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {
}
