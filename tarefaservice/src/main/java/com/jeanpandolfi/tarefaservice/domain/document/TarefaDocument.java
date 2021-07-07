package com.jeanpandolfi.tarefaservice.domain.document;

import com.jeanpandolfi.tarefaservice.config.ElasticSearchConfiguration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(indexName = "tarefadocument")
@Setting(settingPath = "config/elasticsearch/elasticsearch-settings.json")
public class TarefaDocument implements Serializable {

    @Id
    @Field(fielddata = true, type = FieldType.Keyword, analyzer = ElasticSearchConfiguration.ANALYZER)
    private Long id;

    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = ElasticSearchConfiguration.ANALYZER),
                otherFields = {@InnerField(suffix = "sort", type = FieldType.Keyword, analyzer = ElasticSearchConfiguration.ANALYZER)})
    private String titulo;

    @Field(fielddata = true, type = FieldType.Date, format = DateFormat.date_hour_minute_second, analyzer = ElasticSearchConfiguration.ANALYZER)
    private LocalDateTime dataInicioPrevista;

    @Field(fielddata = true, type = FieldType.Date, format = DateFormat.date_hour_minute_second, analyzer = ElasticSearchConfiguration.ANALYZER)
    private LocalDateTime dataTerminoPrevista;

    @Field(fielddata = true, type = FieldType.Date, format = DateFormat.date_hour_minute_second, analyzer = ElasticSearchConfiguration.ANALYZER)
    private LocalDateTime dataInicio;

    @Field(fielddata = true, type = FieldType.Date, format = DateFormat.date_hour_minute_second, analyzer = ElasticSearchConfiguration.ANALYZER)
    private LocalDateTime dataConclusao;

    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = ElasticSearchConfiguration.ANALYZER),
                otherFields = {@InnerField(suffix = "sort", type = FieldType.Keyword, analyzer = ElasticSearchConfiguration.ANALYZER)})
    private String tipo;

    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = ElasticSearchConfiguration.ANALYZER),
                otherFields = {@InnerField(suffix = "sort", type = FieldType.Keyword, analyzer = ElasticSearchConfiguration.ANALYZER)})
    private String status;

    @Field(fielddata = true, type = FieldType.Date, format = DateFormat.date_hour_minute_second, analyzer = ElasticSearchConfiguration.ANALYZER)
    private LocalDateTime tempoPrevisto;

    @Field(fielddata = true, type = FieldType.Date, format = DateFormat.date_hour_minute_second, analyzer = ElasticSearchConfiguration.ANALYZER)
    private LocalDateTime tempoGasto;

    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = ElasticSearchConfiguration.ANALYZER),
                otherFields = {@InnerField(suffix = "sort", type = FieldType.Keyword, analyzer = ElasticSearchConfiguration.ANALYZER)})
    private String responsavelNome;

    @Field(fielddata = true, type = FieldType.Keyword, analyzer = ElasticSearchConfiguration.ANALYZER)
    private Long responsavelId;

}
