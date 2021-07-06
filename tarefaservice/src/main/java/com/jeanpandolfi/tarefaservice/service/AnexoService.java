package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.domain.Anexo;
import com.jeanpandolfi.tarefaservice.repository.AnexoRepository;
import com.jeanpandolfi.tarefaservice.service.dto.AnexoDTO;
import com.jeanpandolfi.tarefaservice.service.dto.DocumentoDTO;
import com.jeanpandolfi.tarefaservice.service.feign.DocumentoFeignClient;
import com.jeanpandolfi.tarefaservice.service.filtro.AnexoFiltro;
import com.jeanpandolfi.tarefaservice.service.mapper.AnexoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import static java.util.Objects.requireNonNull;


@Service
@Transactional
@RequiredArgsConstructor
public class AnexoService implements AbstractService<AnexoDTO, AnexoFiltro>{

    private final AnexoRepository anexoRepository;
    private final AnexoMapper anexoMapper;
    private final DocumentoFeignClient documentoFeignClient;

    @Override
    public AnexoDTO save(AnexoDTO dto) {
        dto.setChaveMinio(String.format("%s-%s.%s", dto.getTitulo(), UUID.randomUUID(), dto.getTipo()));
        dto.setTamanho((long) dto.getBytes().length());
        documentoFeignClient.upload(new DocumentoDTO(dto.getChaveMinio(), dto.getBytes()));
        return anexoMapper.toDto(anexoRepository.save(anexoMapper.toEntity(dto)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AnexoDTO> obterTodos(AnexoFiltro filtro, Pageable pageable) {
        return anexoRepository.findAll(pageable).map(anexoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public AnexoDTO obterPorId(Long id) {
        Anexo anexo = anexoRepository.findById(id).orElseThrow( () -> new RuntimeException("Não econtrado") );
        AnexoDTO anexoDTO = anexoMapper.toDto(anexo);
        anexoDTO.setBytes(requireNonNull(documentoFeignClient.obterPorUuid(anexoDTO.getChaveMinio()).getBody()).getUuid());
        return anexoDTO;
    }

    @Override
    public void deletarPorId(Long id) {
        documentoFeignClient.deletar(anexoRepository.findChaveMinioById(id));
        anexoRepository.deleteById(id);
    }
}
