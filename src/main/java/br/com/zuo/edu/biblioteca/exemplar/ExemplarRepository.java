package br.com.zuo.edu.biblioteca.exemplar;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {

    public Optional<Exemplar> findFirstDisponivelIsTrueByLivroIsbn(String isbn);

    public Optional<Exemplar> findFirstDisponivelIsTrueByTipoCirculacaoEqualsLIVREAndLivroIsbn(String isbn);

}
