package br.com.zuo.edu.biblioteca.livro;

import br.com.zuo.edu.biblioteca.usuario.Usuario;

import javax.persistence.*;

@Entity
public class ReservaExemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ExemplarLivro exemplar;

    @ManyToOne(optional = false)
    private Usuario usuario;

    public ReservaExemplar(ExemplarLivro exemplar, Usuario usuario) {
        this.exemplar = exemplar;
        this.usuario = usuario;
    }

    @Deprecated
    public ReservaExemplar() {
    }
}
