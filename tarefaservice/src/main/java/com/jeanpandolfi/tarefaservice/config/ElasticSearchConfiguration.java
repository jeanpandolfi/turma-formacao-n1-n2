package com.jeanpandolfi.tarefaservice.config;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
@RequiredArgsConstructor
public class ElasticSearchConfiguration extends AbstractElasticsearchConfiguration {

    private final ApplicationProperties properties;

    public static final String ANALYZER = "trim_case_insensitive";

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration
                = ClientConfiguration.builder()
                .connectedTo(properties.getElasticsearch().getUrl())
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
