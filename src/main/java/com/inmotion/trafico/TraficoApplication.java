package com.inmotion.trafico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TraficoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraficoApplication.class, args);
		System.out.println(" Pachuca - Sistema de Tráfico Iniciado");
		System.out.println(" API corriendo en: http://localhost:8080/api");
		System.out.println(" Base de datos: toyota_pachuca");
	}

}
