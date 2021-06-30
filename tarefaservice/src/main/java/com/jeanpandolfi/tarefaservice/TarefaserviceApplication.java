package com.jeanpandolfi.tarefaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TarefaserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarefaserviceApplication.class, args);
	}

}
