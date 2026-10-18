package com.rogertech.infobrasil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InfoBrasilApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoBrasilApplication.class, args);
	}

}
