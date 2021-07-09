package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.domain.Tarefa;
import com.jeanpandolfi.tarefaservice.domain.document.TarefaDocument;
import com.jeanpandolfi.tarefaservice.repository.TarefaRepository;
import com.jeanpandolfi.tarefaservice.service.dto.TarefaDTO;
import com.jeanpandolfi.tarefaservice.service.dto.TarefaListDTO;
import com.jeanpandolfi.tarefaservice.service.event.TarefaEvent;
import com.jeanpandolfi.tarefaservice.service.filtro.TarefaFiltro;
import com.jeanpandolfi.tarefaservice.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    public TarefaDTO save(TarefaDTO dto) {
        Tarefa tarefa = tarefaRepository.save(tarefaMapper.toEntity(dto));
        applicationEventPublisher.publishEvent(new TarefaEvent(tarefa.getId()));
        return tarefaMapper.toDto(tarefa);
    }

    @Transactional(readOnly = true)
    public TarefaDTO obterPorId(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return tarefaMapper.toDto(tarefa);
    }

    public void deletarPorId(Long id) {
        tarefaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public TarefaDocument getDocument(Long id){
        return tarefaRepository.getDocument(id);
    }

    @Transactional(readOnly = true)
    public List<TarefaDocument> getDocuments(){
        return null;
    }
}
