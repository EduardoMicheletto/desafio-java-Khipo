package com.api.testeKhipo;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API desafio Khipo", description = "Api utilizada para teste, contém token JWT, e CRUD de usuario, produto, pedido."))
public class ApiDesafioKhipoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDesafioKhipoApplication.class, args);
	}

}
