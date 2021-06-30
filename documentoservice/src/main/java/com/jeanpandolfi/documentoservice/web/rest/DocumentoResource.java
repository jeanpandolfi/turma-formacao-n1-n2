package com.jeanpandolfi.documentoservice.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documento")
public class DocumentoResource {

    @GetMapping("/upload")
    public ResponseEntity<String> upload(){
        return ResponseEntity.ok("KITUM");
    }
}
