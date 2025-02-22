package io.github.leolimaferreira.desafio_itau_junior.service;

import io.github.leolimaferreira.desafio_itau_junior.model.Transacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransacaoService {

    private Transacao transacaoSalva = new Transacao();

    public Transacao salvar(Transacao transacao) {
            transacaoSalva.setValor(transacao.getValor());
            transacaoSalva.setDataHora(transacao.getDataHora());
            log.info("Salvando Transacao: " + transacaoSalva.getValor() + " / " + transacaoSalva.getDataHora());
            return transacaoSalva;
    }
}
