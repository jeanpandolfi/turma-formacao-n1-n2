package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.domain.Responsavel;
import com.jeanpandolfi.tarefaservice.repository.ResponsavelRepository;
import com.jeanpandolfi.tarefaservice.service.dto.ResponsavelDTO;
import com.jeanpandolfi.tarefaservice.service.mapper.ResponsavelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ResponsavelService{

    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelMapper responsavelMapper;

    public ResponsavelDTO save(ResponsavelDTO dto) {
        return responsavelMapper.toDto(responsavelMapper.toEntity(dto));
    }

    @Transactional(readOnly = true)
    public Page<ResponsavelDTO> obterTodos(Pageable pageable) {
        return responsavelRepository.findAll(pageable).map(responsavelMapper::toDto);
    }

    @Transactional(readOnly = true)
    public ResponsavelDTO obterPorId(Long id) {
        Responsavel responsavel = responsavelRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return responsavelMapper.toDto(responsavel);
    }

    public void deletarPorId(Long id) {
        responsavelRepository.deleteById(id);
    }
}
