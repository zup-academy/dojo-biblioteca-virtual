package br.com.zuo.edu.biblioteca.livro;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class SolicitarEmprestimoRequest {

    @NotNull
    private Long idUsuario;

    @Positive
    private Integer prazoEmDias;

    public SolicitarEmprestimoRequest(Long idUsuario, Integer prazoEmDias) {
        this.idUsuario = idUsuario;
        this.prazoEmDias = prazoEmDias;
    }

    public SolicitarEmprestimoRequest() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Integer getPrazoEmDias() {
        return prazoEmDias;
    }
}
