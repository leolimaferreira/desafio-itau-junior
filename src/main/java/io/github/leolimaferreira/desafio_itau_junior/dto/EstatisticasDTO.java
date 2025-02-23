package io.github.leolimaferreira.desafio_itau_junior.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Estatística")
public record EstatisticasDTO(
        Long count,
        Double sum,
        Double avg,
        Double min,
        Double max
) {
}
