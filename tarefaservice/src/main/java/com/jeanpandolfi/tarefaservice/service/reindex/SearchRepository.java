package com.jeanpandolfi.tarefaservice.service.reindex;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SearchRepository <D, T> extends ElasticsearchRepository<D, T> {

    default Class<D> getDocumentClass(){
        throw new RuntimeException("Methodo não implementado");
    }
}
