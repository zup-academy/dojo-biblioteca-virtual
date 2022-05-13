package br.com.zuo.edu.biblioteca.exemplar;

import javax.validation.constraints.NotNull;

import br.com.zuo.edu.biblioteca.livro.Livro;

public class ExemplarRequest {

    @NotNull
    private TipoCirculacao tipoCirculacao;

    public ExemplarRequest(TipoCirculacao tipoCirculacao) {
        this.tipoCirculacao = tipoCirculacao;
    }

    public ExemplarRequest() {

    }

    public TipoCirculacao getTipoCirculacao() {
        return tipoCirculacao;
    }

    public Exemplar toModel(Livro livro) {
        return new Exemplar(tipoCirculacao, livro);
    }

}
