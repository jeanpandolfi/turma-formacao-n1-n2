package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.domain.Responsavel;
import com.jeanpandolfi.tarefaservice.repository.ResponsavelRepository;
import com.jeanpandolfi.tarefaservice.service.dto.ResponsavelDTO;
import com.jeanpandolfi.tarefaservice.service.filtro.ResponsavelFiltro;
import com.jeanpandolfi.tarefaservice.service.mapper.ResponsavelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ResponsavelService implements AbstractService<ResponsavelDTO, ResponsavelFiltro>{

    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelMapper responsavelMapper;

    @Override
    public ResponsavelDTO save(ResponsavelDTO dto) {
        if(dto.getId() == null){
            dto.setStatus("ATIVO");
        }
        return responsavelMapper.toDto(responsavelRepository.save(responsavelMapper.toEntity(dto)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResponsavelDTO> obterTodos(ResponsavelFiltro filtro, Pageable pageable) {
        return responsavelRepository.findAll(filtro.filter(), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponsavelDTO obterPorId(Long id) {
        Responsavel responsavel = responsavelRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return responsavelMapper.toDto(responsavel);
    }

    @Override
    public void deletarPorId(Long id) {
        responsavelRepository.deleteById(id);
    }
}
