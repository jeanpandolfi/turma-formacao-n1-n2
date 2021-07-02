package com.jeanpandolfi.tarefaservice.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AnexoDTO implements Serializable {

    private Long id;

    private String titulo;

    private Long tamanho;

    private String tipo;

    private String bytes;

    private String chaveMinio;

    private Long idTarefa;
}
