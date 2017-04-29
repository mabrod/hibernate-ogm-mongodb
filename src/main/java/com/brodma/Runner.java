package com.brodma;

import com.brodma.util.AuthorScenarios;
import com.brodma.util.ExecuteStrategy;
import com.brodma.util.ReviewScenarios;
import com.brodma.util.SearchScenarios;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class Runner {

	private final List<ExecuteStrategy> scenarios = new ArrayList();

	@Autowired
	private AuthorScenarios authorScenarios;

	@Autowired
	private ReviewScenarios reviewScenarios;

	@Autowired
	private SearchScenarios searchScenarios;

	@Autowired
	private Logger logger;

	public static void main(String[] args) {
		new SpringApplicationBuilder(Runner.class)
				.web(false)
				.bannerMode(Banner.Mode.OFF)
				.run(args);
	}

	@Bean
	@Scope("prototype")
	public Logger logger(InjectionPoint injectionPoint) {
		return LogManager.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}

	@Bean
	@Profile("!test")
	ApplicationRunner runApplication() {
		return args -> {

			scenarios.add(authorScenarios);
			scenarios.add(reviewScenarios);
			scenarios.add(searchScenarios);

			for(ExecuteStrategy each:scenarios) {
				each.execute();
			}
		};
	}
}
