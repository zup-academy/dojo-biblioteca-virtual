package br.com.zuo.edu.biblioteca.livro;

import br.com.zuo.edu.biblioteca.usuario.TipoUsuario;
import br.com.zuo.edu.biblioteca.usuario.Usuario;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false, unique = true)
    private String isbn;

    public Livro(String titulo, BigDecimal preco, String isbn) {
        this.titulo = titulo;
        this.preco = preco;
        this.isbn = isbn;
    }

    /**
     * @deprecated Construtor para uso exclusivo do Hibernate.
     */
    @Deprecated
    public Livro() {
    }

    public Long getId() {
        return id;
    }

    public void solicitarEmprestimo(Usuario usuario, ExemplarLivro exemplar) {
        boolean usuarioPadrao = usuario.getTipoUsuario().equals(TipoUsuario.PADRAO);
        boolean exemplarRestrtito = exemplar.getTipoCirculacao().equals(TipoCirculacao.RESTRITO);

        if(usuarioPadrao && exemplarRestrtito) {
        }

    }
}
