package com.jeanpandolfi.tarefaservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "anexo")
public class Anexo implements Serializable {

    private static final String SEQUENCE = "seq_anexo";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "caminho", nullable = false)
    private String caminho;

    @Lob
    @Column(name = "conteudo", nullable = false)
    private byte[] conteudo;

    @Column(name = "tamanho", nullable = false)
    private Long tamanho;

    @Column(name = "tipo", nullable = false)
    private String tipo;
}
