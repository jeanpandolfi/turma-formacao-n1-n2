package com.jeanpandolfi.tarefaservice.domain.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(indexName = "tarefadocument")
public class TarefaDocument implements Serializable {

    private Long id;

    private String titulo;

    private LocalDateTime dataInicioPrevista;

    private LocalDateTime dataTerminoPrevista;

    private LocalDateTime dataInicio;

    private LocalDateTime dataConclusao;

    private String tipo;

    private String status;

    private LocalDateTime tempoPrevisto;

    private LocalDateTime tempoGasto;

    private String responsavelNome;

}
