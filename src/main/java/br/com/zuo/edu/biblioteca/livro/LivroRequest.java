package br.com.zuo.edu.biblioteca.livro;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.ISBN.Type;

public class LivroRequest {

    @NotBlank
    private String titulo;

    @NotNull
    @PositiveOrZero
    private BigDecimal preco;

    @NotBlank
    @ISBN(type = Type.ANY)
    private String isbn;

    public LivroRequest() {}

    public LivroRequest(@NotBlank String titulo, @NotNull @PositiveOrZero BigDecimal preco,
                        @NotBlank @ISBN(type = Type.ANY) String isbn) {
        this.titulo = titulo;
        this.preco = preco;
        this.isbn = isbn;
    }

    public Livro toModel() {
        String novoIsbn = isbn.replaceAll("[^0-9X]", "");

        return new Livro(titulo, preco, novoIsbn);
    }

    public String getTitulo() {
        return titulo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getIsbn() {
        return isbn;
    }

}
