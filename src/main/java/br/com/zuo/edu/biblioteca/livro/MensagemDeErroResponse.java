package br.com.zuo.edu.biblioteca.livro;

import org.springframework.validation.FieldError;

public class MensagemDeErroResponse {

    private String campo;
    private String mensagem;

    public MensagemDeErroResponse(FieldError fieldError) {
        this.campo = fieldError.getField();
        this.mensagem = fieldError.getDefaultMessage();
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
