package edu.glyndwr.statanalyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.glyndwr.statanalyser.logic.Analyser;


@SpringBootApplication
public class StatanalyserApplication {

	public static void main(String[] args) {
		SpringApplication.run(Analyser.class, args);
	}

}

