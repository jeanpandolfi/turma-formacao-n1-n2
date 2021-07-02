package com.jeanpandolfi.tarefaservice.domain.elasticsearch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    public TarefaDocument(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public TarefaDocument(Long id, String titulo, LocalDateTime dataInicioPrevista, LocalDateTime dataTerminoPrevista, LocalDateTime dataInicio, LocalDateTime dataConclusao, String tipo, String status, LocalDateTime tempoPrevisto, LocalDateTime tempoGasto, String responsavelNome) {
        this.id = id;
        this.titulo = titulo;
        this.dataInicioPrevista = dataInicioPrevista;
        this.dataTerminoPrevista = dataTerminoPrevista;
        this.dataInicio = dataInicio;
        this.dataConclusao = dataConclusao;
        this.tipo = tipo;
        this.status = status;
        this.tempoPrevisto = tempoPrevisto;
        this.tempoGasto = tempoGasto;
        this.responsavelNome = responsavelNome;
    }
}
