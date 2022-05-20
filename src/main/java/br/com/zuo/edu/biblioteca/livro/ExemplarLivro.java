package br.com.zuo.edu.biblioteca.livro;

import br.com.zuo.edu.biblioteca.usuario.TipoUsuario;
import br.com.zuo.edu.biblioteca.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ExemplarLivro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private TipoCirculacao tipoCirculacao;
    @ManyToOne
    private Livro livro;
    private Boolean reservado = false;

    @OneToMany(mappedBy = "exemplar", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ReservaExemplar> reservas = new ArrayList<>();

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

    public TipoCirculacao getTipoCirculacao() {
        return tipoCirculacao;
    }

    public ReservaExemplar solicitarEmprestimo(Usuario usuario) {
        boolean usuarioPadrao = usuario.getTipoUsuario().equals(TipoUsuario.PADRAO);
        boolean exemplarRestrito = tipoCirculacao.equals(TipoCirculacao.RESTRITO);

        if(usuarioPadrao && exemplarRestrito) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Livro restrito para esse usuário");
        }

        // quantidade de emprestimo dos usuários padrão com exemplares reservados

        ReservaExemplar novaReserva = new ReservaExemplar(this, usuario);
        this.reservado = true;
        this.reservas.add(novaReserva);
        usuario.adicionarReserva(novaReserva);

        return novaReserva;
    }
}
