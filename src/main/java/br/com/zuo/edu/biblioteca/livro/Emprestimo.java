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
    private Integer prazosEmDias;

    @Column(nullable = false)
    private LocalDate dataEntrega;

    @Column
    private LocalDate dataDevolucao;

    @Column(nullable = false)
    private boolean ativo = true;

    public Emprestimo(ExemplarLivro exemplar, Usuario usuario) {
        this.exemplar = exemplar;
        this.usuario = usuario;
    }

    public Emprestimo(ExemplarLivro exemplar, Usuario usuario, LocalDate dataEntrega, Integer prazosEmDias) {
        this.exemplar = exemplar;
        this.usuario = usuario;
        this.dataEntrega = dataEntrega;
        this.prazosEmDias = prazosEmDias;
    }

    @Deprecated
    public Emprestimo() {
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ExemplarLivro getExemplar() {
        return exemplar;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void devolver() {
        this.ativo = false;
        this.dataDevolucao = LocalDate.now();
        this.exemplar.devolver();
    }
}
