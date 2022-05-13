package br.com.zuo.edu.biblioteca.livro;

import javax.validation.constraints.NotNull;

public class CadastrarNovoExemplarRequest {
    @NotNull
    TipoCirculacao tipoCirculacao;

    public CadastrarNovoExemplarRequest() {
    }

    public CadastrarNovoExemplarRequest(TipoCirculacao tipoCirculacao) {
        this.tipoCirculacao = tipoCirculacao;
    }

    public TipoCirculacao getTipoCirculacao() {
        return tipoCirculacao;
    }

    public ExemplarLivro toModel(Livro livro){
        return new ExemplarLivro( tipoCirculacao, livro);
    }

}
