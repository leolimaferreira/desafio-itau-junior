package io.github.leolimaferreira.desafio_itau_junior.exception;

import lombok.Getter;

public class CampoInvalidoException extends RuntimeException{

    @Getter
    private String campo;

    public CampoInvalidoException(String gampo, String mensagem) {
        super(mensagem);
        this.campo = campo;
    }

}
