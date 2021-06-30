package com.jeanpandolfi.tarefaservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AbstractService<T> {

    T save(T dto);

    Page<T> obterTodos(Pageable pageable);

    T obterPorId(Long id);

    void deletarPorId(Long id);
}
