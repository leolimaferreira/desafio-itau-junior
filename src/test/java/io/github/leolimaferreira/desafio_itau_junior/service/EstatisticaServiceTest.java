package io.github.leolimaferreira.desafio_itau_junior.service;

import io.github.leolimaferreira.desafio_itau_junior.dto.EstatisticasDTO;
import io.github.leolimaferreira.desafio_itau_junior.model.Transacao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticaServiceTest {

    @InjectMocks
    EstatisticasService estatisticasService;

    @Mock
    TransacaoService transacaoService;

    Transacao transacao;

    EstatisticasDTO estatisticas;

    @BeforeEach
    void setUp() {
        transacao = new Transacao();
        transacao.setValor(20.0);
        transacao.setDataHora(OffsetDateTime.now());
        estatisticas = new EstatisticasDTO(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void calcularEstatisticasComSucesso() {
        when(transacaoService.buscarTransacoes(60)).thenReturn(Collections.singletonList(transacao));

        EstatisticasDTO resultado = estatisticasService.calcularEstatisticasTransacoes(60);

        verify(transacaoService, times(1)).buscarTransacoes(60);

        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticas);
    }

    @Test
    void calcularEstatisticasQuandoListaVazia() {

        EstatisticasDTO estatisticaEsperado = new EstatisticasDTO(0L, 0.0, 0.0, 0.0, 0.0);
        when(transacaoService.buscarTransacoes(60)).thenReturn(Collections.emptyList());

        EstatisticasDTO resultado = estatisticasService.calcularEstatisticasTransacoes(60);

        verify(transacaoService, times(1)).buscarTransacoes(60);

        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticaEsperado);
    }
}
