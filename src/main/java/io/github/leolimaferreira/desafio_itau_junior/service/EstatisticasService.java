package io.github.leolimaferreira.desafio_itau_junior.service;

import io.github.leolimaferreira.desafio_itau_junior.dto.EstatisticasDTO;
import io.github.leolimaferreira.desafio_itau_junior.model.Transacao;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstatisticasService {

    private static final Logger log = LoggerFactory.getLogger(EstatisticasService.class);
    private final TransacaoService transacaoService;

    public EstatisticasDTO calcularEstatisticasTransacoes(Integer intervaloBusca) {

        log.info("Buscando estatísticas das transações nos ultimos " + intervaloBusca + " segundos.");

        long start = System.currentTimeMillis();

        List<Transacao> transacaos = transacaoService.buscarTransacoes(intervaloBusca);

        if (transacaos.isEmpty()){
            return  new EstatisticasDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticas = transacaos.stream().mapToDouble(Transacao::getValor).summaryStatistics();

        long finish = System.currentTimeMillis();

        System.out.println("Tempo de requisição: " + (finish - start) + " milissegundos");

        log.info("Estatísticas retornadas com sucesso!");
        return new EstatisticasDTO(estatisticas.getCount(), estatisticas.getSum(), estatisticas.getAverage(), estatisticas.getMin(), estatisticas.getMax());

    }
}
