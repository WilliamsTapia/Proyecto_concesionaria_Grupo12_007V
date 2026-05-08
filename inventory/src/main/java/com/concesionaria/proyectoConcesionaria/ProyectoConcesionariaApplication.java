package com.concesionaria.proyectoConcesionaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProyectoConcesionariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoConcesionariaApplication.class, args);
	}

}
