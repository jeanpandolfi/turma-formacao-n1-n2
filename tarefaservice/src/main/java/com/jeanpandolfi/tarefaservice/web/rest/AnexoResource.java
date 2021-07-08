package com.jeanpandolfi.tarefaservice.web.rest;

import com.jeanpandolfi.tarefaservice.service.AnexoService;
import com.jeanpandolfi.tarefaservice.service.dto.AnexoDTO;
import com.jeanpandolfi.tarefaservice.service.dto.AnexoListDTO;
import com.jeanpandolfi.tarefaservice.service.dto.DocumentoDTO;
import com.jeanpandolfi.tarefaservice.service.filtro.AnexoFiltro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/anexo")
@RequiredArgsConstructor
public class AnexoResource {

    private final AnexoService anexoService;

    @PostMapping("/{idTarefa}")
    public ResponseEntity<List<AnexoListDTO>> salvar(@PathVariable Long idTarefa, @RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(anexoService.save(idTarefa, file));
    }

    @GetMapping
    public ResponseEntity<Page<AnexoDTO>> obterTodos(AnexoFiltro filtro, Pageable pageable){
        return ResponseEntity.ok(anexoService.obterTodos(filtro, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnexoDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(anexoService.obterPorId(id));
    }

    @GetMapping("/tarefa/{idTarefa}")
    public ResponseEntity<List<AnexoListDTO>> buscarAnexosTarefa(@PathVariable Long idTarefa){
        return ResponseEntity.ok(anexoService.buscarAnexosTarefa(idTarefa));
    }

    @GetMapping("/download/{idAnexo}")
    public ResponseEntity<InputStream> download(@PathVariable Long idAnexo){
        return ResponseEntity.ok(anexoService.dowload(idAnexo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        anexoService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }
}