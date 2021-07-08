package com.jeanpandolfi.documentoservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.InputStream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {

    private String uuid;
    private InputStream file;
}
