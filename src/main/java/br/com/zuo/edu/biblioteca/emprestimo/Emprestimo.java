package br.com.zuo.edu.biblioteca.emprestimo;

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

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Emprestimo() {}

    public Emprestimo(Usuario usuario, Exemplar exemplar) {
        this.usuario = usuario;
        this.exemplar = exemplar;
        exemplar.setDisponivel(false);
    }

    public Long getId() {
        return id;
    }

}
