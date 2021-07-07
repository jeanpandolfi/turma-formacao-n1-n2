package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.domain.document.TarefaDocument;
import com.jeanpandolfi.tarefaservice.repository.search.TarefaSearchRepository;
import com.jeanpandolfi.tarefaservice.service.dto.TarefaListDTO;
import com.jeanpandolfi.tarefaservice.service.event.TarefaEvent;
import com.jeanpandolfi.tarefaservice.service.filtro.TarefaFiltro;
import com.jeanpandolfi.tarefaservice.service.mapper.TarefaDocumentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
@RequiredArgsConstructor
public class TarefaElasticSearchService {

    private final TarefaSearchRepository tarefaSearchRepository;

    private final TarefaDocumentMapper tarefaDocumentMapper;

    private final TarefaService tarefaService;

    private final ElasticsearchOperations operations;

    @TransactionalEventListener
    void indexar(TarefaEvent event){
        tarefaSearchRepository.save(tarefaService.getDocument(event.getId()));
        log.debug("Indexando TarefaDocument id {}", event.getId());
    }

    public Page<TarefaListDTO> search(TarefaFiltro filtro, Pageable pageable){
          return tarefaSearchRepository.search(filtro.getFilter(), pageable).map(tarefaDocumentMapper::toDto);
    }

    public void reindex() {
        operations.indexOps(TarefaDocument.class).delete();
        operations.indexOps(TarefaDocument.class).create();

        tarefaSearchRepository.saveAll(tarefaService.getDocuments());
    }
}
