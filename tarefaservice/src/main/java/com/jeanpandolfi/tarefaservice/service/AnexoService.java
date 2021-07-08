package com.jeanpandolfi.tarefaservice.service;

import com.jeanpandolfi.tarefaservice.domain.Anexo;
import com.jeanpandolfi.tarefaservice.repository.AnexoRepository;
import com.jeanpandolfi.tarefaservice.service.dto.AnexoDTO;
import com.jeanpandolfi.tarefaservice.service.dto.AnexoListDTO;
import com.jeanpandolfi.tarefaservice.service.feign.DocumentoFeignClient;
import com.jeanpandolfi.tarefaservice.service.filtro.AnexoFiltro;
import com.jeanpandolfi.tarefaservice.service.mapper.AnexoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class AnexoService {

    private final AnexoRepository anexoRepository;
    private final AnexoMapper anexoMapper;
    private final DocumentoFeignClient documentoFeignClient;

    public List<AnexoListDTO> save(Long idTarefa, MultipartFile file) throws IOException {
        AnexoDTO anexoDTO = new AnexoDTO();
        anexoDTO.setTamanho(file.getSize());
        anexoDTO.setTipo(file.getContentType());
        anexoDTO.setIdTarefa(idTarefa);
        anexoDTO.setTitulo(file.getOriginalFilename());
        anexoDTO.setChaveMinio(documentoFeignClient.upload(file).getBody());

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
        return anexoDTO;
    }

    public void deletarPorId(Long id) {
        documentoFeignClient.deletar(anexoRepository.findChaveMinioById(id));
        anexoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<AnexoListDTO> buscarAnexosTarefa(Long idTarefa) {
        return anexoRepository.findAnexosByTarefa(idTarefa);
    }

    @Transactional(readOnly = true)
    public InputStream dowload(Long id) {
        ResponseEntity<InputStream> doc = documentoFeignClient.obterPorUuid(anexoRepository.findChaveMinioById(id));
        return doc.getBody();
    }
}
