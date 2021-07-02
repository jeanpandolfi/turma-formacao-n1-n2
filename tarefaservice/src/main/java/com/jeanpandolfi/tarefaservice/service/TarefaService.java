package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.domain.Tarefa;
import com.jeanpandolfi.tarefaservice.domain.elasticsearch.TarefaDocument;
import com.jeanpandolfi.tarefaservice.repository.TarefaRepository;
import com.jeanpandolfi.tarefaservice.repository.search.TarefaSearchRepository;
import com.jeanpandolfi.tarefaservice.service.dto.TarefaDTO;
import com.jeanpandolfi.tarefaservice.service.event.TarefaEvent;
import com.jeanpandolfi.tarefaservice.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TarefaService implements AbstractService<TarefaDTO>{

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public TarefaDTO save(TarefaDTO dto) {
        Tarefa tarefa = tarefaRepository.save(tarefaMapper.toEntity(dto));
        applicationEventPublisher.publishEvent(new TarefaEvent(tarefa.getId()));
        return tarefaMapper.toDto(tarefa);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TarefaDTO> obterTodos(Pageable pageable) {
        return tarefaRepository.findAll(pageable).map(tarefaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public TarefaDTO obterPorId(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return tarefaMapper.toDto(tarefa);
    }

    @Override
    public void deletarPorId(Long id) {
        tarefaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public TarefaDocument getDocument(Long id){
        return tarefaRepository.getDocument(id);
    }
}
