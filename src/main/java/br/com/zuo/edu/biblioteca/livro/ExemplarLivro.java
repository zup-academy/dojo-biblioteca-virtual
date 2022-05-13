package br.com.zuo.edu.biblioteca.livro;

import javax.persistence.*;

@Entity
public class ExemplarLivro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private TipoCirculacao tipoCirculacao;
    @ManyToOne
    private Livro livro;

    @Deprecated
    public ExemplarLivro() {
    }

    public ExemplarLivro(TipoCirculacao tipoCirculacao, Livro livro) {
        this.tipoCirculacao = tipoCirculacao;
        this.livro = livro;
    }

    public Long getId() {
        return id;
    }
}
