package io.github.leolimaferreira.desafio_itau_junior.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoDTO(
		@NotNull(message = "campo obrigatório")
		@DecimalMin(value = "0", message = "o valor deve ser maior que zero")
		BigDecimal valor,
		@NotNull(message = "campo obrigatório")
		@Past(message = "só são aceitas transações passadas")
		LocalDateTime dataHora) {

}
