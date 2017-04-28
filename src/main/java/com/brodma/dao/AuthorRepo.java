package com.brodma.dao;

import com.brodma.domain.Author;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public interface AuthorRepo {

    void add(Author author);

    void update(Author author);

    void delete(Author author);

    Optional<Author> findOne(Author author);

    Collection<Author> findAll();

    Collection<Author> findByFirstAndLastName(Author author);

    Collection<Author> findByFirstName(Author author);

    Collection<Author> findByLastName(Author author);

    Collection<Author> findByDob(Date dob);

}
