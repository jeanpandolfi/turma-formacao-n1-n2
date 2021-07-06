package com.jeanpandolfi.tarefaservice.web.rest;

import com.jeanpandolfi.tarefaservice.service.TarefaService;
import com.jeanpandolfi.tarefaservice.service.dto.TarefaDTO;
import com.jeanpandolfi.tarefaservice.service.filtro.TarefaFiltro;
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
@RequestMapping("/api/tarefa")
@RequiredArgsConstructor
public class TarefaResource {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> salvar(@RequestBody TarefaDTO tarefaDTO){
        log.debug("Requisição para savar uma tarefa {}", tarefaDTO);
        return ResponseEntity.ok(tarefaService.save(tarefaDTO));
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> atualizar(@RequestBody TarefaDTO responsavelDTO){
        return ResponseEntity.ok(tarefaService.save(responsavelDTO));
    }

    @GetMapping
    public ResponseEntity<Page<TarefaDTO>> obterTodos(TarefaFiltro filtro, Pageable pageable){
        return ResponseEntity.ok(tarefaService.obterTodos(filtro, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(tarefaService.obterPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        tarefaService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }
}
