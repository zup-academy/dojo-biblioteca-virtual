package br.com.zuo.edu.biblioteca.exemplar;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {

    public Optional<Exemplar> findFirstByDisponivelIsTrueAndLivroIsbn(String isbn);

    public Optional<Exemplar> findFirstByDisponivelIsTrueAndTipoCirculacaoAndLivroIsbn(TipoCirculacao tipo, String isbn);

}
