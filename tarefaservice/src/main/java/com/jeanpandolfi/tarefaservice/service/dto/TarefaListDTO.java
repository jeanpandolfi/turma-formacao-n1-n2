package com.jeanpandolfi.tarefaservice.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
public class TarefaListDTO implements Serializable {

    private Long id;

    private String titulo;

    private LocalDateTime dataInicioPrevista;

    private LocalDateTime dataTerminoPrevista;

    private String tipo;

    private String status;

    private LocalDateTime tempoPrevisto;

    private String responsavelNome;
}
