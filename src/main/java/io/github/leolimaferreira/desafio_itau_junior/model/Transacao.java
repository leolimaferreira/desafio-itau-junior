package io.github.leolimaferreira.desafio_itau_junior.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Transacao {
	
	private Double valor;
	private OffsetDateTime dataHora;
}
