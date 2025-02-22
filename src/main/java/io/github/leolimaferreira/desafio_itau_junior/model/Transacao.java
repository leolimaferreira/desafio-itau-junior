package io.github.leolimaferreira.desafio_itau_junior.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Transacao {
	
	private BigDecimal valor;
	private LocalDateTime dataHora;
}
