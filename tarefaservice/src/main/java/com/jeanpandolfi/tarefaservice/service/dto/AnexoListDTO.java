package com.jeanpandolfi.tarefaservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnexoListDTO implements Serializable {

    private Long id;

    private String titulo;

    private Long tamanho;

}
