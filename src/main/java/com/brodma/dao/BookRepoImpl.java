package com.brodma.dao;

import com.brodma.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;


@Repository
public class BookRepoImpl implements BookRepo {

    @Autowired
    @PersistenceContext(unitName="ogm-mongodb")
    private EntityManager entityManager;

    @Override
    public void add(Book book) {
        entityManager.persist(book);
    }

    @Override
    public void update(Book book) {
        entityManager.merge(book);
    }

    @Override
    public void delete(Book book) {
        entityManager.remove(book);
    }

    @Override
    public Collection<Book> findAll() {
       return entityManager.createQuery("SELECT b FROM Book b").getResultList();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {

            return Optional.of(entityManager.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class)
            .setParameter("isbn", isbn)
            .setMaxResults(1)
                    .getResultList()
                    .stream()
                    .findFirst()).orElse(Optional.empty());
    }
}
