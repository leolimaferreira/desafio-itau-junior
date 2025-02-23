package io.github.leolimaferreira.desafio_itau_junior.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.leolimaferreira.desafio_itau_junior.model.Transacao;
import io.github.leolimaferreira.desafio_itau_junior.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TransacaoControllerTest {

    @InjectMocks
    TransacaoController transacaoController;

    @Mock
    TransacaoService transacaoService;

    Transacao transacao;

    @Autowired
    final ObjectMapper objectMapper = new ObjectMapper();

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
        transacao = new Transacao();
        transacao.setValor(20.0);
        transacao.setDataHora(OffsetDateTime.of(2025, 1, 10, 14, 30, 0, 0, ZoneOffset.UTC));
    }

    @Test
    void deveAdicionarTransacaoComSucesso() throws Exception{

        doNothing().when(transacaoService).salvar(transacao);

        mockMvc.perform(post("/transacao")
                .content(objectMapper.writeValueAsString(transacao))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deveDeletarTransacoesComSucesso() throws Exception {
        doNothing().when(transacaoService).deletarTransacoes();
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
    }
}
