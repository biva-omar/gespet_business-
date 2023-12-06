package fr.formationspring.gespet.business.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan("fr.formationspring.gespet.business.entity")
@EnableJpaRepositories("fr.formationspring.gespet.business.dao")
@ComponentScan(basePackages = { "fr.formationspring.gespet.business.service" })
public class GespetBusinessConfig {
	
	@Bean(value = "gespet-modelmapper")
	@Scope(value = "singleton")
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

}
