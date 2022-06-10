package br.com.zuo.edu.biblioteca.emprestimo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.zuo.edu.biblioteca.exemplar.Exemplar;
import br.com.zuo.edu.biblioteca.usuario.Usuario;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Usuario usuario;

    @ManyToOne
    private Exemplar exemplar;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @Column(nullable = false)
    private Integer prazoEmDias;

    @Column(nullable = false)
    private boolean ativo;

    @Column(nullable = false)
    private LocalDateTime dataEntregaPrevista;

    private LocalDateTime dataEntrega;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Emprestimo() {}

    public Emprestimo(Usuario usuario, Exemplar exemplar, Integer prazoEmDias) {
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.prazoEmDias = prazoEmDias;
        this.ativo = true;
        this.criadoEm = LocalDateTime.now();
        this.dataEntregaPrevista = LocalDateTime.now().plusDays(prazoEmDias);
        exemplar.setDisponivel(false);
    }

    public Long getId() {
        return id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void devolver() {
        this.ativo = false;
        this.dataEntrega = LocalDateTime.now();
        this.exemplar.setDisponivel(true);
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
