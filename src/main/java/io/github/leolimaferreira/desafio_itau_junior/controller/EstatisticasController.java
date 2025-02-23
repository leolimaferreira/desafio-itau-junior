package io.github.leolimaferreira.desafio_itau_junior.controller;

import io.github.leolimaferreira.desafio_itau_junior.dto.EstatisticasDTO;
import io.github.leolimaferreira.desafio_itau_junior.service.EstatisticasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatisticas")
@Tag(name = "Estatisticas")
public class EstatisticasController {

    private final EstatisticasService estatisticasService;

    @GetMapping
    @Operation(summary = "Calcular Estatísticas",
            description = "Retorna estatisticas das transações pelo período de tempo passado, o valor padrão é de 60 segundos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estatísticas retornadas."),
            @ApiResponse(responseCode = "400", description = "Requisição fora dos padrões."),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
    public ResponseEntity<EstatisticasDTO> buscarEstatisticas(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {

        return ResponseEntity.ok(
                estatisticasService.calcularEstatisticasTransacoes(intervaloBusca));
    }
}
