package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.domain.Tarefa;
import com.jeanpandolfi.tarefaservice.repository.TarefaRepository;
import com.jeanpandolfi.tarefaservice.service.dto.TarefaDTO;
import com.jeanpandolfi.tarefaservice.service.mapper.TarefaMapper;
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
    private final TarefaMapper tarefaMapper;

    public TarefaDTO save(TarefaDTO dto) {
        return tarefaMapper.toDto(tarefaMapper.toEntity(dto));
    }

    @Transactional(readOnly = true)
    public Page<TarefaDTO> obterTodos(Pageable pageable) {
        return tarefaRepository.findAll(pageable).map(tarefaMapper::toDto);
    }

    @Transactional(readOnly = true)
    public TarefaDTO obterPorId(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return tarefaMapper.toDto(tarefa);
    }

    public void deletarPorId(Long id) {
        tarefaRepository.deleteById(id);
    }
}
