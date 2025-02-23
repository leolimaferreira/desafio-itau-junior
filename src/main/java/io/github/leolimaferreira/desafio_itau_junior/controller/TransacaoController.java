 package io.github.leolimaferreira.desafio_itau_junior.controller;

import io.github.leolimaferreira.desafio_itau_junior.dto.TransacaoDTO;
import io.github.leolimaferreira.desafio_itau_junior.dto.mappers.TransacaoMapper;
import io.github.leolimaferreira.desafio_itau_junior.model.Transacao;
import io.github.leolimaferreira.desafio_itau_junior.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Transações")
public class TransacaoController {

    private final TransacaoMapper mapper;
    private final TransacaoService transacaoService;

    @PostMapping
    @Operation(summary = "Receber Transações", description = "Cadastrar nova transação")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrada com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação nos campos."),
            @ApiResponse(responseCode = "400", description = "Requisição fora dos padrões."),
            @ApiResponse(responseCode = "500", description = "Erro interno.")
    })
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
    @Operation(summary = "Limpar Transações", description = "Deletar todas as transações salvas")
    @ApiResponse(responseCode = "200", description = "Deletadas com sucesso")
     public ResponseEntity<Void> deletar() {
        transacaoService.deletarTransacoes();
        return ResponseEntity.ok().build();
    }
}
