package com.jeanpandolfi.tarefaservice.service.reindex;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElasticsearchService {

    @Value("${nuvem.elasticsearch.reindex.pageSize:10000}")
    private Integer pageSize;

    private final List<Reindexador> reindexadores;
    private final List<SearchRepository> searchRepositories;
    private final ElasticsearchOperations elasticsearchOperations;

    @Async
    public void reindex(){
        log.info("Starting reindex.");
        for (Reindexador reindexer : reindexadores) {
            reindex(reindexer);
        }
    }

    private void reindex(Reindexador bean) {
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<?> page = bean.reindexPage(pageable);
        log.info("Objects found {}.", page.getTotalElements());
        if (!page.hasContent()) {
            return;
        }
        log.info("Total Pages {}.", page.getTotalPages());
        SearchRepository searchRepository = getSearchRepository(page);
        recreateIndexDocument(searchRepository.getDocumentClass());

        while (page.hasContent()) {
            log.info("Page Number {}.", page.getNumber());
            searchRepository.saveAll(page);
            page = bean.reindexPage(page.getPageable().next());
        }

        log.info("Finish reindex of {}.", bean.getClass());
    }

    private SearchRepository getSearchRepository(Page<?> page) {
        Class documentClass = page.getContent().get(0).getClass();
        Iterator<SearchRepository> var3 = this.searchRepositories.iterator();

        SearchRepository searchRepository;
        do {
            if (!var3.hasNext()) {
                throw new RuntimeException("Falha de Reindexação...");
            }

            searchRepository = var3.next();
        } while(!searchRepository.getDocumentClass().equals(documentClass));

        return searchRepository;
    }

    private <T> void recreateIndexDocument(Class<T> entityClass) {
        log.info("Recriate index class: {}", entityClass.getName());
        elasticsearchOperations.indexOps(entityClass).delete();
        elasticsearchOperations.indexOps(entityClass).create();
    }
}
