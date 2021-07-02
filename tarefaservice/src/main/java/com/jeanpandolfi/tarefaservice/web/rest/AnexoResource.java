package com.jeanpandolfi.tarefaservice.web.rest;

import com.jeanpandolfi.tarefaservice.service.AnexoService;
import com.jeanpandolfi.tarefaservice.service.dto.AnexoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/anexo")
@RequiredArgsConstructor
public class AnexoResource {

    private final AnexoService anexoService;

    @PostMapping
    public ResponseEntity<AnexoDTO> salvar(@RequestBody AnexoDTO anexoDTO){
        return ResponseEntity.ok(anexoService.save(anexoDTO));
    }

    @GetMapping
    public ResponseEntity<Page<AnexoDTO>> obterTodos(Pageable pageable){
        return ResponseEntity.ok(anexoService.obterTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnexoDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(anexoService.obterPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        anexoService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }
}