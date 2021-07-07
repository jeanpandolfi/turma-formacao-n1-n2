package com.jeanpandolfi.tarefaservice.service.mapper;

import com.jeanpandolfi.tarefaservice.domain.document.TarefaDocument;
import com.jeanpandolfi.tarefaservice.service.dto.TarefaListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaDocumentMapper extends EntityMapper<TarefaListDTO, TarefaDocument>{
    
}
