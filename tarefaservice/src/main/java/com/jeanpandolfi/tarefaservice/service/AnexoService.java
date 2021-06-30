package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.repository.AnexoRepository;
import com.jeanpandolfi.tarefaservice.service.dto.AnexoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class AnexoService {

    private final AnexoRepository anexoRepository;

    public AnexoDTO save(AnexoDTO dto) {
        return null;
    }

    @Transactional(readOnly = true)
    public Page<AnexoDTO> obterTodos(Pageable pageable) {
        return null;
    }

    @Transactional(readOnly = true)
    public AnexoDTO obterPorId(Long id) {
        return null;
    }

    public void deletarPorId(Long id) {
        anexoRepository.deleteById(id);
    }
}
