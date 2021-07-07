package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.domain.Anexo;
import com.jeanpandolfi.tarefaservice.repository.AnexoRepository;
import com.jeanpandolfi.tarefaservice.service.dto.AnexoDTO;
import com.jeanpandolfi.tarefaservice.service.dto.AnexoListDTO;
import com.jeanpandolfi.tarefaservice.service.dto.DocumentoDTO;
import com.jeanpandolfi.tarefaservice.service.feign.DocumentoFeignClient;
import com.jeanpandolfi.tarefaservice.service.filtro.AnexoFiltro;
import com.jeanpandolfi.tarefaservice.service.mapper.AnexoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import static java.util.Objects.requireNonNull;


@Service
@Transactional
@RequiredArgsConstructor
public class AnexoService {

    private final AnexoRepository anexoRepository;
    private final AnexoMapper anexoMapper;
    private final DocumentoFeignClient documentoFeignClient;

    public List<AnexoListDTO> save(Long idTarefa, MultipartFile anexo) throws IOException {
        AnexoDTO anexoDTO = new AnexoDTO();
        anexoDTO.setTamanho(anexo.getSize());
        anexoDTO.setTipo(anexo.getContentType());
        anexoDTO.setIdTarefa(idTarefa);
        anexoDTO.setTitulo(anexo.getOriginalFilename());
        anexoDTO.setChaveMinio(String.format("%s-%s", anexoDTO.getTitulo(), UUID.randomUUID()));

        documentoFeignClient.upload(new DocumentoDTO(anexoDTO.getChaveMinio(), anexo.getBytes()));
        anexoRepository.save(anexoMapper.toEntity(anexoDTO));

        return anexoRepository.findAnexosByTarefa(idTarefa);
    }

    @Transactional(readOnly = true)
    public Page<AnexoDTO> obterTodos(AnexoFiltro filtro, Pageable pageable) {
        return anexoRepository.findAll(pageable).map(anexoMapper::toDto);
    }

    @Transactional(readOnly = true)
    public AnexoDTO obterPorId(Long id) {
        Anexo anexo = anexoRepository.findById(id).orElseThrow( () -> new RuntimeException("Não econtrado") );
        AnexoDTO anexoDTO = anexoMapper.toDto(anexo);
        anexoDTO.setBytes(requireNonNull(documentoFeignClient.obterPorUuid(anexoDTO.getChaveMinio()).getBody()).getUuid());
        return anexoDTO;
    }

    public void deletarPorId(Long id) {
        documentoFeignClient.deletar(anexoRepository.findChaveMinioById(id));
        anexoRepository.deleteById(id);
    }
}
