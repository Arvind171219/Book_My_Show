package com.example.BookMyShowProject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import io.swagger.v3.oas.annotations.servers.Servers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition



@OpenAPIDefinition(servers = {@Server(url = "https://bookmyshowbackend-clone-production.up.railway.app/")})
//		variables = @ServerVariable(name = "host",
//				defaultValue = "hostname_sit",
//				allowableValues = {"hostname_sit","hostname_uat"}),
//		description = "Host name by environment")})
public class BookMyShowProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowProjectApplication.class, args);
	}

}
