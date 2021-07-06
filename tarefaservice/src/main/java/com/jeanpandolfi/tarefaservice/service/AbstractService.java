package com.jeanpandolfi.tarefaservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AbstractService<T, F> {

    T save(T dto);

    Page<T> obterTodos(F filtro, Pageable pageable);

    T obterPorId(Long id);

    void deletarPorId(Long id);
}
