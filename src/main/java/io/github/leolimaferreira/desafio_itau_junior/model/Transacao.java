package io.github.leolimaferreira.desafio_itau_junior.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class Transacao {
	
	private BigDecimal valor;
	private OffsetDateTime dataHora;
}
