package com.jeanpandolfi.tarefaservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tarefa")
public class Tarefa implements Serializable {

    private static final String SEQUENCE = "seq_tarefa";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_inicio_prevista", nullable = false)
    private LocalDateTime dataInicioPrevista;

    @Column(name = "data_termino_prevista", nullable = false)
    private LocalDateTime dataTerminoPrevista;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "comentarios")
    private String comentarios;

    @Column(name = "tempo_previsto", nullable = false)
    private LocalDateTime tempoPrevisto;

    @Column(name = "tempo_gasto")
    private LocalDateTime tempoGasto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsavel_id", referencedColumnName = "id")
    private Responsavel responsavel;

}
