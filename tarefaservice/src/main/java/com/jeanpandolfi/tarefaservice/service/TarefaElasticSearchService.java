package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.domain.document.TarefaDocument;
import com.jeanpandolfi.tarefaservice.repository.TarefaRepository;
import com.jeanpandolfi.tarefaservice.repository.search.TarefaSearchRepository;
import com.jeanpandolfi.tarefaservice.service.dto.TarefaListDTO;
import com.jeanpandolfi.tarefaservice.service.event.TarefaEvent;
import com.jeanpandolfi.tarefaservice.service.filtro.TarefaFiltro;
import com.jeanpandolfi.tarefaservice.service.mapper.TarefaDocumentMapper;
import com.jeanpandolfi.tarefaservice.service.reindex.Reindexador;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
@RequiredArgsConstructor
public class TarefaElasticSearchService implements Reindexador<TarefaDocument> {

    private final TarefaSearchRepository tarefaSearchRepository;

    private final TarefaDocumentMapper tarefaDocumentMapper;

    private final TarefaService tarefaService;

    private final TarefaRepository tarefaRepository;

    private final ElasticsearchOperations operations;

    private final RestHighLevelClient restHighLevelClient;

    @TransactionalEventListener
    void indexar(TarefaEvent event){
        tarefaSearchRepository.save(tarefaService.getDocument(event.getId()));
        log.debug("Indexando TarefaDocument id {}", event.getId());
    }

    public Page<TarefaListDTO> search(TarefaFiltro filtro, Pageable pageable){
          return tarefaSearchRepository.search(filtro.getFilter(), pageable).map(tarefaDocumentMapper::toDto);
    }

    @Override
    public Page<TarefaDocument> reindexPage(Pageable pageable) {
        return tarefaRepository.getDocuments(pageable);
    }
}
