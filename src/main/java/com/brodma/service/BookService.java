package com.brodma.service;

import com.brodma.dao.BookRepo;
import com.brodma.domain.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.ValidationException;
import java.util.*;

@Service
@Transactional
public class BookService {

    private final Logger LOG = LogManager.getLogger(BookService.class);

    private BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Transactional(rollbackFor = ValidationException.class)
    public void add(Book book) {
        try {
            bookRepo.add(book);
        }catch(ValidationException ve) {
            LOG.error(ve.getMessage(), ve);
        }
    }

    @Transactional(rollbackFor = ValidationException.class)
    public void update(Book book) {
        try {
            bookRepo.update(book);
        }catch(ValidationException ve) {
            LOG.error(ve.getMessage(), ve);
        }
    }

    @Transactional(rollbackFor = ValidationException.class)
    public void delete(Book book) {
        try {
            bookRepo.delete(book);
        }catch(ValidationException ve) {
            LOG.error(ve.getMessage(), ve);
        }
    }

    @Transactional(readOnly = true)
    public Optional<Book> findByIsbn(String isbn) {
        return bookRepo.findByIsbn(isbn);
    }
}
