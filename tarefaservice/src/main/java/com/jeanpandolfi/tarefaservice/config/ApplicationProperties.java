package com.jeanpandolfi.tarefaservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class ApplicationProperties {

    private ElasticSearchProperties elasticsearch = new ElasticSearchProperties();

}
