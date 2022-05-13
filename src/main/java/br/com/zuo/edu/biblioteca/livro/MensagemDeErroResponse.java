package br.com.zuo.edu.biblioteca.livro;

public class MensagemDeErroResponse {

    private String campo;
    private String mensagem;

    public MensagemDeErroResponse(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
