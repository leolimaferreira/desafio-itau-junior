 package io.github.leolimaferreira.desafio_itau_junior.controller;

import io.github.leolimaferreira.desafio_itau_junior.dto.TransacaoDTO;
import io.github.leolimaferreira.desafio_itau_junior.dto.mappers.TransacaoMapper;
import io.github.leolimaferreira.desafio_itau_junior.model.Transacao;
import io.github.leolimaferreira.desafio_itau_junior.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

 @Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoMapper mapper;
    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid TransacaoDTO dto) {
        Transacao transacaoToSave = mapper.toEntity(dto);
        log.info("Salvando Transacao: " + transacaoToSave.getValor() + " / " + transacaoToSave.getDataHora());
        transacaoService.salvar(transacaoToSave);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{valor}")
                .buildAndExpand(transacaoToSave.getValor())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping
     public ResponseEntity<Void> deletar() {
        transacaoService.deletarTransacoes();
        return ResponseEntity.ok().build();
    }
}
