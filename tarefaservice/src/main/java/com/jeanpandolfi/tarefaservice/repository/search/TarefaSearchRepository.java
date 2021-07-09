package com.jeanpandolfi.tarefaservice.repository.search;

import com.jeanpandolfi.tarefaservice.domain.document.TarefaDocument;
import com.jeanpandolfi.tarefaservice.service.reindex.SearchRepository;


public interface TarefaSearchRepository extends SearchRepository<TarefaDocument, Long> {

    @Override
    default Class<TarefaDocument> getDocumentClass(){
        return TarefaDocument.class;
    }

}
