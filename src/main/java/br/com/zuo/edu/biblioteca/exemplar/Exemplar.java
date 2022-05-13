package br.com.zuo.edu.biblioteca.exemplar;

import br.com.zuo.edu.biblioteca.livro.Livro;

import javax.persistence.*;

@Entity
@Table(name = "exemplares")
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private TipoCirculacao tipoCirculacao;

    @ManyToOne
    private Livro livro;

    public Exemplar(TipoCirculacao tipoCirculacao, Livro livro) {
        this.tipoCirculacao = tipoCirculacao;
        this.livro = livro;
    }

    @Deprecated
    public Exemplar(){

    }

    public Long getId() {
        return id;
    }
}
