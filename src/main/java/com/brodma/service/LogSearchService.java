package com.brodma.service;

import com.brodma.dao.LogSearchRepo;
import com.brodma.domain.LogEntry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

@Service
@Transactional
public class LogSearchService {

    private LogSearchRepo logSearchRepo;

    public LogSearchService(LogSearchRepo logSearchRepo) {
        this.logSearchRepo = logSearchRepo;
    }

    public Collection<LogEntry> find(String toSearch) {
        return logSearchRepo.find(toSearch);
    }

    public void add(LogEntry logEntry) {
        logSearchRepo.add(logEntry);
    }

    public int numOfMatches(String toSearch) {
        return logSearchRepo.numOfMatches(toSearch);
    }

    public void initSearchIndex() {
        logSearchRepo.initSearchIndex();
    }

}
