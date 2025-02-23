package io.github.leolimaferreira.desafio_itau_junior.controller;

import io.github.leolimaferreira.desafio_itau_junior.dto.EstatisticasDTO;
import io.github.leolimaferreira.desafio_itau_junior.service.EstatisticasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticaControllerTest {

    @InjectMocks
    EstatisticasController estatisticasController;

    @Mock
    EstatisticasService estatisticasService;

    EstatisticasDTO estatisticas;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(estatisticasController).build();

        estatisticas = new EstatisticasDTO(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void deveBuscarEstatisticasComSucesso() throws Exception{

        when(estatisticasService.calcularEstatisticasTransacoes(60)).thenReturn(estatisticas);

        mockMvc.perform(get("/estatistica")
                .param("intervaloBusca", "60")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.count").value(estatisticas.count()));
    }
}
