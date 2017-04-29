package com.brodma.dao;

import com.brodma.domain.LogEntry;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class LogSearchImpl implements LogSearchRepo {

    @Autowired
    private Logger logger;

    @Autowired
    @PersistenceContext(unitName="ogm-mongodb")
    private EntityManager entityManager;

    @Override
    public Collection<LogEntry> find(String toSearch) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(LogEntry.class).get();
        Query query = queryBuilder.keyword().onField("text").matching(toSearch).createQuery();
        // wrap Lucene query into Hibernate search query
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, LogEntry.class);
        return jpaQuery.getResultList();
    }

    @Override
    public void add(LogEntry logEntry) {
        entityManager.persist(logEntry);
    }

    @Override
    public int numOfMatches(String toSearch) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(LogEntry.class).get();
        Query query = queryBuilder.keyword().onField("text").matching(toSearch).createQuery();
        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, LogEntry.class);
        return jpaQuery.getResultSize();
    }

    @Override
    public void initSearchIndex() {
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer(LogEntry.class)
                    .batchSizeToLoadObjects(25)
                    .threadsToLoadObjects(4)
                    .idFetchSize( 150 )
                    .startAndWait();

        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
