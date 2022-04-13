package ru.vcrop.GameOfLife;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.SessionScope;

@SpringBootApplication
public class GameOfLifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameOfLifeApplication.class, args);
	}


}
