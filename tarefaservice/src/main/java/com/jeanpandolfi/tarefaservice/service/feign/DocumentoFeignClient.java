package com.jeanpandolfi.tarefaservice.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "documentoservice", url = "${application.feign.documento}")
public interface DocumentoFeignClient {

    @GetMapping("/api/documento/upload")
    ResponseEntity<String> upload();
}
