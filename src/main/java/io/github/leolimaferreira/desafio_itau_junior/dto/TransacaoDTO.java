package io.github.leolimaferreira.desafio_itau_junior.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoDTO(
		BigDecimal valor,
		LocalDateTime dataHora) {

}
