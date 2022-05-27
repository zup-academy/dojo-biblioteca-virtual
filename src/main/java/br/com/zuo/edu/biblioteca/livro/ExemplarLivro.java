package br.com.zuo.edu.biblioteca.livro;

import br.com.zuo.edu.biblioteca.usuario.TipoUsuario;
import br.com.zuo.edu.biblioteca.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.time.LocalDate;
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
    private List<Emprestimo> reservas = new ArrayList<>();

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

    public Emprestimo solicitarEmprestimo(Usuario usuario, Integer prazoEmDias, Integer quantidadeEmprestimoDoUsuario) {
        boolean usuarioPadrao = usuario.getTipoUsuario().equals(TipoUsuario.PADRAO);
        boolean exemplarRestrito = tipoCirculacao.equals(TipoCirculacao.RESTRITO);

        if(usuarioPadrao && exemplarRestrito) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Livro restrito para esse usuário");
        }

        if(usuarioPadrao && prazoEmDias == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Usuário não definiu o tempo de empréstimo");
        }
        // Data de entrega
        if (prazoEmDias == null) {
            prazoEmDias = 60;
        }

        LocalDate prazo = LocalDate.now().plusDays(prazoEmDias);

        // quantidade de emprestimo dos usuários padrão com exemplares reservados
        if (usuarioPadrao && quantidadeEmprestimoDoUsuario > 5) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Usuário já possui número máximo de empréstimos");
        }

        Emprestimo emprestimo = new Emprestimo(this, usuario, prazo);
        this.reservado = true;
        this.reservas.add(emprestimo);
        usuario.adicionarReserva(emprestimo);

        return emprestimo;
    }
}
