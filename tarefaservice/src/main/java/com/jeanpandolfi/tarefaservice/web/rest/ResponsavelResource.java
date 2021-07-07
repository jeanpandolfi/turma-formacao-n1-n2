package com.jeanpandolfi.tarefaservice.web.rest;

import com.jeanpandolfi.tarefaservice.domain.Responsavel;
import com.jeanpandolfi.tarefaservice.service.dto.ResponsavelDTO;
import com.jeanpandolfi.tarefaservice.service.ResponsavelService;
import com.jeanpandolfi.tarefaservice.service.dto.SelectItemDTO;
import com.jeanpandolfi.tarefaservice.service.filtro.ResponsavelFiltro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/responsavel")
@RequiredArgsConstructor
public class ResponsavelResource {

    private final ResponsavelService responsavelService;

    @PostMapping
    public ResponseEntity<ResponsavelDTO> salvar(@RequestBody ResponsavelDTO responsavelDTO){
        return ResponseEntity.ok(responsavelService.save(responsavelDTO));
    }

    @PutMapping
    public ResponseEntity<ResponsavelDTO> atualizar(@RequestBody ResponsavelDTO responsavelDTO){
        return ResponseEntity.ok(responsavelService.save(responsavelDTO));
    }

    @PostMapping("/filter")
    public ResponseEntity<Page<ResponsavelDTO>> obterTodos(@RequestBody ResponsavelFiltro filtro, Pageable pageable){
        return ResponseEntity.ok(responsavelService.obterTodos(filtro, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(responsavelService.obterPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        responsavelService.deletarPorId(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<SelectItemDTO>> prrencherDropDown(){
        return ResponseEntity.ok(responsavelService.preencherDropDown());
    }
}
