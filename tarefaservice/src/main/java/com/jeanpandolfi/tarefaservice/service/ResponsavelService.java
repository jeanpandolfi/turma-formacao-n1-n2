package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.repository.ResponsavelRepository;
import com.jeanpandolfi.tarefaservice.service.dto.ResponsavelDTO;
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

    public ResponsavelDTO save(ResponsavelDTO dto) {
        return null;
    }

    @Transactional(readOnly = true)
    public Page<ResponsavelDTO> obterTodos(Pageable pageable) {
        return null;
    }

    @Transactional(readOnly = true)
    public ResponsavelDTO obterPorId(Long id) {
        return null;
    }

    public void deletarPorId(Long id) {

    }
}
