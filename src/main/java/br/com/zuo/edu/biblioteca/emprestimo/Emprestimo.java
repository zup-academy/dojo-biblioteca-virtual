package br.com.zuo.edu.biblioteca.emprestimo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.zuo.edu.biblioteca.exemplar.Exemplar;
import br.com.zuo.edu.biblioteca.usuario.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private LocalDate criadoEm = LocalDate.now();

    private Integer prazoEmDias;


    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Emprestimo() {}

    public Emprestimo(Usuario usuario, Exemplar exemplar, Integer prazoEmDias) {
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.prazoEmDias = prazoEmDias;
        exemplar.setDisponivel(false);
    }

    public Long getId() {
        return id;
    }

    public void setPrazoEmDias(Integer prazoEmDias) {
        this.prazoEmDias = prazoEmDias;
    }

}
