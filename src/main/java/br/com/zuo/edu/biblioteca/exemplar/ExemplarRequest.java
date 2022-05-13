package br.com.zuo.edu.biblioteca.exemplar;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public class ExemplarRequest {

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoCirculacao tipoCirculacao;

    public ExemplarRequest(TipoCirculacao tipoCirculacao) {
        this.tipoCirculacao = tipoCirculacao;
    }

    public ExemplarRequest(){

    }

    public TipoCirculacao getTipoCirculacao() {
        return tipoCirculacao;
    }

    public Exemplar toModel(){
        return
    }
}
