package com.jeanpandolfi.tarefaservice.web.rest;

import com.jeanpandolfi.tarefaservice.service.reindex.ElasticsearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/reindex")
@RequiredArgsConstructor
public class ElasticsearchResource {

    private final ElasticsearchService service;

    @GetMapping
    public ResponseEntity<Void> reindexAll(){
        log.debug("Reindex ALL");
        service.reindex();
        return ResponseEntity.ok().build();
    }

}
