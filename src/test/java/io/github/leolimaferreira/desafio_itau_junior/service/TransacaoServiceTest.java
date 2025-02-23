package io.github.leolimaferreira.desafio_itau_junior.service;

import io.github.leolimaferreira.desafio_itau_junior.dto.EstatisticasDTO;
import io.github.leolimaferreira.desafio_itau_junior.exception.CampoInvalidoException;
import io.github.leolimaferreira.desafio_itau_junior.model.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
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
    void deveAdicionarTransacaoComSucesso() {

        Transacao salvar = transacaoService.salvar(transacao);

        List<Transacao> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.contains(transacao));
    }

    @Test
    void deveLancarExcecaoCasoValorSejaNegativo() {

        Transacao erro = new Transacao();
        erro.setValor(-20.0);
        erro.setDataHora(OffsetDateTime.now());

        CampoInvalidoException exception = assertThrows(CampoInvalidoException.class,
                () -> transacaoService.salvar(erro));

        assertEquals("Valor não pode ser menor que 0", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoCasoDataDepoisAtual() {

        Transacao erro = new Transacao();
        erro.setValor(20000.0);
        erro.setDataHora(OffsetDateTime.now().plusDays(1));

        CampoInvalidoException exception = assertThrows(CampoInvalidoException.class,
                () -> transacaoService.salvar(erro));

        assertEquals("Data e hora maiores que a data e hora atuais", exception.getMessage());
    }

    @Test
    void deveLimparTransacaoComSucesso(){

        transacaoService.deletarTransacoes();

        List<Transacao> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.isEmpty());
    }

    @Test
    void deveLimparComSucesso() {

        transacaoService.deletarTransacoes();

        List<Transacao> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.isEmpty());
    }

    @Test
    void deveBuscarTransacaoDentroDoIntervalo() {

        Transacao transacao1 = new Transacao();
        transacao1.setValor(10.0);
        transacao1.setDataHora(OffsetDateTime.now().minusHours(1));

        transacaoService.salvar(transacao);
        transacaoService.salvar(transacao1);

        List<Transacao> transacoes = transacaoService.buscarTransacoes(60);

        assertTrue(transacoes.contains(transacao));
        assertFalse(transacoes.contains(transacao1));
    }
}
