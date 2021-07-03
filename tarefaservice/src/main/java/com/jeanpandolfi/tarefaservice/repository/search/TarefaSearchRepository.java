package com.jeanpandolfi.tarefaservice.repository.search;

import com.jeanpandolfi.tarefaservice.domain.document.TarefaDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaSearchRepository extends ElasticsearchRepository<TarefaDocument, Long> {
}
