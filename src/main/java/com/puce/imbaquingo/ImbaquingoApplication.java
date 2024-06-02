package com.puce.imbaquingo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ImbaquingoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImbaquingoApplication.class, args);

		//Sandbox de pruebas por consola
		// Crear una instancia de la clase Pokemon
		// Pokemon pikachu = new Pokemon();

		// Establecer el valor del codigo pokemon
		// pikachu.setCodigo(25);

		// Llamar al método getPokemonInfoFromApi
		// pikachu.getPokemonInfoFromApi();

	}

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
