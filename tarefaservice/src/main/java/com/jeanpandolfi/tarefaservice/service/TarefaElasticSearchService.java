package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.repository.search.TarefaSearchRepository;
import com.jeanpandolfi.tarefaservice.service.event.TarefaEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
@RequiredArgsConstructor
public class TarefaElasticSearchService {

    private final TarefaSearchRepository tarefaSearchRepository;

    private final TarefaService tarefaService;


    @TransactionalEventListener
    void indexar(TarefaEvent event){
        tarefaSearchRepository.save(tarefaService.getDocument(event.getId()));
        log.debug("Indexando TarefaDocument id {}", event.getId());
    }
}
