package com.jeanpandolfi.documentoservice.web.rest;

import com.jeanpandolfi.documentoservice.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/documento")
public class DocumentoResource {

    private final DocumentoService documentoService;

    @PostMapping
    public ResponseEntity<String> upload(@RequestBody MultipartFile file){
        return ResponseEntity.ok(documentoService.salvar(file));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<InputStream> obterPorUuid(@PathVariable String uuid){
        return ResponseEntity.ok(documentoService.buscar(uuid));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(@PathVariable String uuid){
        documentoService.deletar(uuid);
        return ResponseEntity.ok().build();
    }
}
