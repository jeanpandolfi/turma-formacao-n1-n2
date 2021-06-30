package com.jeanpandolfi.tarefaservice.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
public class TarefaDTO implements Serializable {

    private Long id;

    private String titulo;

    private String descricao;

    private LocalDateTime dataInicioPrevista;

    private LocalDateTime dataTerminoPrevista;

    private LocalDateTime dataInicio;

    private LocalDateTime dataConclusao;

    private String tipo;

    private String status;

    private String comentarios;

    private LocalDateTime tempoPrevisto;

    private LocalDateTime tempoGasto;

    private Long idResponsavel;

    private Long nomeResponsavel;
}
