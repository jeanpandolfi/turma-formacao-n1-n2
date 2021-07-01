package com.jeanpandolfi.tarefaservice.service.mapper;

import com.jeanpandolfi.tarefaservice.domain.Anexo;
import com.jeanpandolfi.tarefaservice.service.dto.AnexoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnexoMapper extends EntityMapper<AnexoDTO, Anexo>{

    @Override
    @Mapping(source = "idTarefa", target = "tarefa.id")
    Anexo toEntity(AnexoDTO dto);

    @Override
    @Mapping(source = "tarefa.id", target = "idTarefa")
    AnexoDTO toDto(Anexo entity);
}
