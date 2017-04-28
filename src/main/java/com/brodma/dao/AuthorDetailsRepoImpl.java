package com.brodma.dao;

import com.brodma.domain.AuthorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AuthorDetailsRepoImpl implements AuthorDetailsRepo {

    @Autowired
    @PersistenceContext(unitName="ogm-mongodb")
    private EntityManager entityManager;

    @Override
    public void add(AuthorDetails authorDetails) {
        entityManager.persist(authorDetails);
    }

    @Override
    public void update(AuthorDetails authorDetails) {
        entityManager.merge(authorDetails);
    }

    @Override
    public void delete(AuthorDetails authorDetails) {
        entityManager.remove(authorDetails);
    }
}
