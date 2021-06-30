package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.repository.TarefaRepository;
import com.jeanpandolfi.tarefaservice.service.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class TarefaService{

    private final TarefaRepository tarefaRepository;


    public TarefaDTO save(TarefaDTO dto) {
        return null;
    }

    @Transactional(readOnly = true)
    public Page<TarefaDTO> obterTodos(Pageable pageable) {
        return null;
    }

    @Transactional(readOnly = true)
    public TarefaDTO obterPorId(Long id) {
        return null;
    }

    public void deletarPorId(Long id) {

    }
}
