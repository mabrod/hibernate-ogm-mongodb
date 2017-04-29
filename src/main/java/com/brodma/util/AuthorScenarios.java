package com.brodma.util;

import com.brodma.domain.Author;
import com.brodma.service.AuthorDetailsService;
import com.brodma.service.AuthorService;
import com.brodma.util.featureflag.FeatureFlags;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collection;

@Component
public class AuthorScenarios implements ExecuteStrategy {

    @Autowired
    private Logger logger;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorDetailsService authorDetailsService;


    @Override
    public void execute() {
        if (FeatureFlags.AUTHOR_SCENARIOS.isActive()) {
            logger.info("Executing Author scenarios...");
            Collection<Author> allAuthors = authorService.findAll();
            allAuthors.stream().forEach(logger::info);
        }
    }
}
