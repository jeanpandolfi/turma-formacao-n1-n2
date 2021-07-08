package com.jeanpandolfi.tarefaservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.InputStream;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {

    private String uuid;
    private InputStream file;

}
