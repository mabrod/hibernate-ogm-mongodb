package com.brodma.dao;

import com.brodma.domain.Book;
import java.util.Collection;
import java.util.Optional;

public interface BookRepo {

    void add(Book book);

    void update(Book book);

    void delete(Book book);

    Collection<Book> findAll();

    Optional<Book> findByIsbn(String isbn);
}
