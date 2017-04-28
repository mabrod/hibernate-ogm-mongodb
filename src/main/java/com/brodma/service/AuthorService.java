package com.brodma.service;

import com.brodma.dao.AuthorRepo;
import com.brodma.domain.Author;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.ValidationException;
import java.util.*;


@Service
@Transactional
public class AuthorService {

    private final Logger LOG = LogManager.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Transactional(rollbackFor = ValidationException.class)
    public void add(Author author) {
        try {
            authorRepo.add(author);
        }catch(ValidationException cve) {
            LOG.error(cve.getMessage(), cve);
        }
    }

    @Transactional(rollbackFor = ValidationException.class)
    public void update(Author author) {

        try {
            authorRepo.update(author);
        }catch(ValidationException cve) {
            LOG.error(cve.getMessage(), cve);
        }
    }

    public void deleteByFirstAndLastName(Author author) {

        Collection<Author> result = authorRepo.findByFirstAndLastName(author);
        for (Author each: result) {
            authorRepo.delete(each);
        }
    }

    @Transactional(readOnly = true)
    public Collection<Author> findByDob(Date dob) {
        return authorRepo.findByDob(dob);
    }

    @Transactional(readOnly = true)
    public Collection<Author> findAll() {
        return authorRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Collection<Author> findByFirstAndLastNames(Author author) {
        return authorRepo.findByFirstAndLastName(author);
    }

    @Transactional(readOnly = true)
    public Collection<Author> findByFirstName(Author author) {
        return authorRepo.findByFirstName(author);
    }

    @Transactional(readOnly = true)
    public Collection<Author> findByLastName(Author author) {
        return authorRepo.findByLastName(author);
    }

    @Transactional(readOnly = true)
    public Optional<Author> findOne(Author author) {
        return authorRepo.findOne(author);
    }

}
