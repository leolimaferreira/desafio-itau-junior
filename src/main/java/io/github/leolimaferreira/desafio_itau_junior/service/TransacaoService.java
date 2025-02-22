package io.github.leolimaferreira.desafio_itau_junior.service;

import io.github.leolimaferreira.desafio_itau_junior.model.Transacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TransacaoService {

    private List<Transacao> transacoes = new ArrayList<>();

    public Transacao salvar(Transacao transacao) {
            log.info("Salvando Transacao: " + transacao.getValor() + " / " + transacao.getDataHora());
            transacoes.add(transacao);
            return transacao;
    }

    public void deletarTransacoes() {
        List<Transacao> transacoesRemovidas = new ArrayList<>(transacoes);
        transacoes.clear();
        log.info("Transacoes deletadas: ");
        for(Transacao t : transacoesRemovidas) {
            log.info("Transação: " + t.getValor() + " / " + t.getDataHora());
        }
    }

    public List<Transacao> buscarTransacoes(Integer intervaloBusca) {

        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        return transacoes.stream()
                .filter(transacao -> transacao.getDataHora()
                        .isAfter(dataHoraIntervalo)).toList();
    }
}
