package com.jeanpandolfi.tarefaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableElasticsearchRepositories("com.jeanpandolfi.tarefaservice.repository.search")
public class TarefaserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarefaserviceApplication.class, args);
	}

}
