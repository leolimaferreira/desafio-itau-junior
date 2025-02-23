package io.github.leolimaferreira.desafio_itau_junior.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Erro de Campo")
public record ErroCampo(String campo, String erro) {
}
