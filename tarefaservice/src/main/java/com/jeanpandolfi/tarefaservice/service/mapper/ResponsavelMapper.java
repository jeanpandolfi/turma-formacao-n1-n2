package com.jeanpandolfi.tarefaservice.service.mapper;

import com.jeanpandolfi.tarefaservice.domain.Responsavel;
import com.jeanpandolfi.tarefaservice.service.dto.ResponsavelDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsavelMapper extends EntityMapper<ResponsavelDTO, Responsavel>{

}
