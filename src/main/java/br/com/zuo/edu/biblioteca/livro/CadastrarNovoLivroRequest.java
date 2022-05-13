package br.com.zuo.edu.biblioteca.livro;

import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CadastrarNovoLivroRequest {

    @NotBlank
    private String titulo;

    @NotNull
    @Positive
    private BigDecimal preco;

    @NotNull
    @ISBN(type = ISBN.Type.ANY)
    private String isbn;

    public CadastrarNovoLivroRequest(String titulo, BigDecimal preco, String isbn) {
        this.titulo = titulo;
        this.preco = preco;
        this.isbn = isbn;
    }

    public CadastrarNovoLivroRequest() {
    }

    public Livro toModel() {
        return new Livro(titulo,preco,isbn);
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
