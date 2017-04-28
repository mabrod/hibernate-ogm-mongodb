package com.brodma.dao;

import com.brodma.domain.LogEntry;
import java.util.Collection;

public interface LogSearchRepo {

    Collection<LogEntry> find(String toSearch);

    void add(LogEntry logEntry);

    int numOfMatches(String toSearch);

    void initSearchIndex();

}
