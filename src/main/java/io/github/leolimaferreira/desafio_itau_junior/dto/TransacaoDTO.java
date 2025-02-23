package io.github.leolimaferreira.desafio_itau_junior.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.OffsetDateTime;

@Schema(name = "Transação")
public record TransacaoDTO(
		@NotNull(message = "campo obrigatório")
		@DecimalMin(value = "0", message = "o valor deve ser maior que zero")
		Double valor,
		@NotNull(message = "campo obrigatório")
		@Past(message = "só são aceitas transações passadas")
		OffsetDateTime dataHora) {

}
