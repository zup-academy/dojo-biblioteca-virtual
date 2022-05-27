package br.com.zuo.edu.biblioteca.livro;

import br.com.zuo.edu.biblioteca.usuario.Usuario;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ExemplarLivro exemplar;

    @ManyToOne(optional = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDate dataEntrega;

    @Column(nullable = false)
    private boolean ativo = true;

    public Emprestimo(ExemplarLivro exemplar, Usuario usuario) {
        this.exemplar = exemplar;
        this.usuario = usuario;
    }

    public Emprestimo(ExemplarLivro exemplar, Usuario usuario, LocalDate dataEntrega) {
        this.exemplar = exemplar;
        this.usuario = usuario;
        this.dataEntrega = dataEntrega;
    }

    @Deprecated
    public Emprestimo() {
    }

    public Long getId() {
        return id;
    }
}
