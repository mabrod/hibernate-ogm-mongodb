package com.brodma.dao;

import com.brodma.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AuthorRepoImpl implements AuthorRepo {

    @Autowired
    /**
     * type=PersistenceContextType.EXTENDED changes from transaction-scope EM to a stateful session bean
     * if you do not need EXTENDED then make your service class calls transactional to put EM into transaction scope
     */
    @PersistenceContext(unitName="ogm-mongodb")
    private EntityManager entityManager;

    @Override
    public void add(Author author) {
        entityManager.persist(author);
    }

    @Override
    public void update(Author author) {
        entityManager.merge(author);
    }

    @Override
    public void delete(Author author) {
         entityManager.remove(author);
    }

    @Override
    public Optional<Author> findOne(Author author) {

        return Optional.of(entityManager.createQuery( "SELECT a FROM Author a where a.firstName = :firstName and a.lastName = :lastName and a.age = :age and a.dob = :dob", Author.class )
        .setParameter("firstName", author.getFirstName())
        .setParameter("lastName", author.getLastName())
        .setParameter("age", author.getAge())
        .setParameter("dob", author.getDob())
        .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst()).orElse(Optional.empty());
    }

    @Override
    public Collection<Author> findAll() {
        return entityManager.createQuery( "SELECT a FROM Author a", Author.class ).getResultList();
    }

    @Override
    public Collection<Author> findByFirstAndLastName(Author author) {
        Query query = entityManager.createQuery( "SELECT a FROM Author a where a.firstName = :firstName and a.lastName = :lastName", Author.class );
        query.setParameter("firstName", author.getFirstName());
        query.setParameter("lastName", author.getLastName());
        return query.getResultList();
    }

    @Override
    public Collection<Author> findByFirstName(Author author) {
        Query query = entityManager.createQuery("SELECT a FROM Author a WHERE a.firstName = :firstName");
        query.setParameter("firstName", author.getFirstName());
        return query.getResultList();
    }

    @Override
    public Collection<Author> findByLastName(Author author) {
        Query query = entityManager.createQuery("SELECT a FROM Author a WHERE a.firstName = :lastName");
        query.setParameter("lastName", author.getLastName());
        return query.getResultList();
    }

    @Override
    public Collection<Author> findByDob(Date dob) {
        Query query = entityManager.createQuery("SELECT a FROM Author a where a.dob = :dob");
        query.setParameter("dob", dob);
        return query.getResultList();
    }

    private void close() {
        if (Objects.nonNull(entityManager)) {
            entityManager.close();
        }
    }
}
