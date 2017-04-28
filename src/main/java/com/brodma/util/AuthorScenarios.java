package com.brodma.util;

import com.brodma.domain.Author;
import com.brodma.service.AuthorDetailsService;
import com.brodma.service.AuthorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collection;

@Component
public class AuthorScenarios implements ExecuteStrategy {

    private static final Logger LOG = LogManager.getLogger(AuthorScenarios.class);

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorDetailsService authorDetailsService;


    @Override
    public void execute() {

        LOG.info("Executing Author scenarios...");
        Collection<Author> allAuthors = authorService.findAll();
        allAuthors.stream().forEach(LOG::info);
    }
}
