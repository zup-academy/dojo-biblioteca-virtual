package br.com.zuo.edu.biblioteca.emprestimo;

import br.com.zuo.edu.biblioteca.exemplar.Exemplar;
import br.com.zuo.edu.biblioteca.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class EmprestimoRequest {

    @NotNull
    private Long usuarioId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataDevolucao;

    public EmprestimoRequest() {}

    public EmprestimoRequest(Long usuarioId, LocalDate dataDevolucao) {
        this.usuarioId = usuarioId;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo toModel(Exemplar exemplar, Usuario usuario) {
        return new Emprestimo(usuario, exemplar);
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
}
