package com.jeanpandolfi.tarefaservice.service.filtro;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class TarefaFiltro implements BaseFilter, Serializable {

    private String titulo;

    private LocalDateTime dataInicioPrevista;

    private LocalDateTime dataTerminoPrevista;

    private String tipo;

    private String status;

    private LocalDateTime tempoPrevisto;

    private String responsavelNome;

    private Long responsavelId;

    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        addWildcard(boolQueryBuilder, "titulo", titulo);
        addWildcard(boolQueryBuilder, "tipo", tipo);
        addWildcard(boolQueryBuilder, "status", status);
        addWildcard(boolQueryBuilder, "responsavelNome", responsavelNome);

        addMustTermQuery(boolQueryBuilder, "dataInicioPrevista", dataInicioPrevista);
        addMustTermQuery(boolQueryBuilder, "dataTerminoPrevista", dataTerminoPrevista);
        addMustTermQuery(boolQueryBuilder, "responsavelId", responsavelId);

        return boolQueryBuilder;
    }
}
