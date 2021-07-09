package com.jeanpandolfi.tarefaservice.service.reindex;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Reindexador<D> {

    Page<D> reindexPage(Pageable pageable);
}
