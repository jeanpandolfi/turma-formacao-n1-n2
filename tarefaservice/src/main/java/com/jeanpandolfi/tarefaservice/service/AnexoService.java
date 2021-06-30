package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.domain.Anexo;
import com.jeanpandolfi.tarefaservice.repository.AnexoRepository;
import com.jeanpandolfi.tarefaservice.service.dto.AnexoDTO;
import com.jeanpandolfi.tarefaservice.service.feign.DocumentoFeignClient;
import com.jeanpandolfi.tarefaservice.service.mapper.AnexoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class AnexoService {

    private final AnexoRepository anexoRepository;
    private final AnexoMapper anexoMapper;
    private final DocumentoFeignClient documentoFeignClient;

    public AnexoDTO save(AnexoDTO dto) {
        return anexoMapper.toDto(anexoMapper.toEntity(dto));
    }

    @Transactional(readOnly = true)
    public Page<AnexoDTO> obterTodos(Pageable pageable) {
        System.err.println(documentoFeignClient.upload().getBody());
        return anexoRepository.findAll(pageable).map(anexoMapper::toDto);
    }

    @Transactional(readOnly = true)
    public AnexoDTO obterPorId(Long id) {
        Anexo anexo = anexoRepository.findById(id).orElseThrow( () -> new RuntimeException("Não econtrado"));
        return anexoMapper.toDto(anexo);
    }

    public void deletarPorId(Long id) {
        anexoRepository.deleteById(id);
    }
}
