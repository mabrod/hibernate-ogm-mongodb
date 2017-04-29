package com.brodma.service;

import com.brodma.domain.LogEntry;
import com.brodma.util.conf.MongoTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Collection;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MongoTestConfig.class}, webEnvironment=NONE)
@ActiveProfiles({"test"})
public class LogSearchServiceTest {

    @Autowired
    private LogSearchService logSearchService;

    private static boolean isLoaded = false;

    @Before
    public void setUp() {

        if (!isLoaded) {
            LogEntry log1 = new LogEntry();
            log1.setText("Error happened when sending email.");
            LogEntry log2 = new LogEntry();
            log2.setText("Exception happened when accessing this field.");
            logSearchService.add(log1);
            logSearchService.add(log2);
            isLoaded = true;
        }
    }

    @Test
    public void shouldFindLogEntry() {
        Collection<LogEntry> result =  logSearchService.find("Error happened when sending email.");
        assertThat(result).isNotEmpty();
    }

    @Test
    public void shouldFindLogEntryIncludingSomeKeyword() {
        Collection<LogEntry> result =  logSearchService.find("Error keyword is in the logs.");
        assertThat(result).isNotEmpty();
    }

    @Test
    public void shouldReportCorrectNumberOfMatches() {
        int result =  logSearchService.numOfMatches("happened");
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void shouldNotFindLogEntry() {
        Collection<LogEntry> result =  logSearchService.find("Not in the logs.");
        assertThat(result).isEmpty();
    }

}