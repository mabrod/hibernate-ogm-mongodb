package com.brodma;

import com.brodma.service.LogSearchService;
import com.brodma.util.AuthorScenarios;
import com.brodma.util.ExecuteStrategy;
import com.brodma.util.ReviewScenarios;
import com.brodma.util.SearchScenarios;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class Runner implements ApplicationRunner {

	private static final Logger LOG = LogManager.getLogger(Runner.class);

	Map<String, ExecuteStrategy> scenarios = new HashMap<String,ExecuteStrategy>();

	@Autowired
	private AuthorScenarios authorScenarios;

	@Autowired
	private ReviewScenarios reviewScenarios;

	@Autowired
	private SearchScenarios searchScenarios;

	@Autowired
	private LogSearchService logSearchService;

	public static void main(String[] args) {
		new SpringApplicationBuilder(Runner.class)
				.web(false)
				.bannerMode(Banner.Mode.OFF)
				.run(args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
           logSearchService.initSearchIndex();
		   List<String> choices = args.getNonOptionArgs();
		   LOG.info("Started application with args {} ", choices);
           for(String each:choices) {
			   scenarios.get(each).execute();
		   }
		   LOG.info("Bye.");
	}

	@PostConstruct
	public void initScenarios() {
		scenarios.put("author", authorScenarios);
		scenarios.put("review", reviewScenarios);
		scenarios.put("search", searchScenarios);
	}
}
