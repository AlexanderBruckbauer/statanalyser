package edu.glyndwr.statanalyser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.glyndwr.statanalyser.logic.Analyser;



@Configuration
public class StatAnalyserConfig {
	@Bean(initMethod="init")
	public Analyser analyser() {
		return new Analyser();
	}
}
