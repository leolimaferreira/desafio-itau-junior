package io.github.leolimaferreira.desafio_itau_junior.dto.mappers;

import io.github.leolimaferreira.desafio_itau_junior.dto.TransacaoDTO;
import io.github.leolimaferreira.desafio_itau_junior.model.Transacao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransacaoMapper {

    Transacao toEntity(TransacaoDTO dto);

    TransacaoDTO toDTO(Transacao transacao);
}
