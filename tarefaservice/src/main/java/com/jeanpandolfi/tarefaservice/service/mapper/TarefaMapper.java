package com.jeanpandolfi.tarefaservice.service.mapper;

import com.jeanpandolfi.tarefaservice.domain.Tarefa;
import com.jeanpandolfi.tarefaservice.service.dto.TarefaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaMapper extends EntityMapper<TarefaDTO, Tarefa>{

    @Override
    @Mapping(source = "idResponsavel", target = "responsavel.id")
    Tarefa toEntity(TarefaDTO dto);

    @Override
    @Mapping(source = "responsavel.id", target = "idResponsavel")
    TarefaDTO toDto(Tarefa entity);
}
