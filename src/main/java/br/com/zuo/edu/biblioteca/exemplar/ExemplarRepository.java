package br.com.zuo.edu.biblioteca.exemplar;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {

    public Optional<Exemplar> findFirstByLivroIsbn(String isbn);
}
