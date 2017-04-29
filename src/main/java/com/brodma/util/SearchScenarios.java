package com.brodma.util;

import com.brodma.domain.LogEntry;
import com.brodma.service.LogSearchService;
import com.brodma.util.featureflag.FeatureFlags;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collection;

@Component
public class SearchScenarios implements ExecuteStrategy {

    @Autowired
    private Logger logger;

    @Autowired
    private LogSearchService logSearchService;

    @Override
    public void execute() {

        if (FeatureFlags.HIBERNATE_SEARCH_INDEX_BUILD.isActive()) {
            logger.info("Executing Hibernate Search index building...");
            logSearchService.initSearchIndex();
        }

        if (FeatureFlags.SEARCH_SCENARIOS.isActive()) {
            logger.info("Executing search scenarios...");
            Collection<LogEntry> results = logSearchService.find("Error happend when sending email");
            results.stream().forEach(logger::info);
        }
    }
}
