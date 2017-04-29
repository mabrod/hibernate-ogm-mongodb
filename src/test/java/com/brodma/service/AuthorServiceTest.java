package com.brodma.service;

import com.brodma.domain.Author;
import com.brodma.util.conf.MongoTestConfig;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionException;
import java.sql.Date;
import java.util.Collection;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MongoTestConfig.class}, webEnvironment=NONE)
@ActiveProfiles({"test"})
public class AuthorServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private AuthorService authorService;

    @Before
    public void setUp() {
        Author author = new Author();
        author.setFirstName("Ada");
        author.setLastName("Lovelace");
        author.setAge(37);
        author.setDob(Date.valueOf("1815-12-10"));
        authorService.add(author);
    }

    @Test
    public void shouldAddAuthor() {
        Author author = new Author();
        author.setFirstName("Bob");
        author.setLastName("Doe");
        author.setAge(40);
        author.setDob(Date.valueOf("1977-02-05"));
        authorService.add(author);
        Collection<Author> results = authorService.findAll();
        assertThat(results.contains(author));
    }

    @Test
    public void shouldUpdateAuthorAge() {
        Author ada = new Author();
        ada.setFirstName("Ada");
        ada.setLastName("Lovelace");
        Collection<Author> results = authorService.findByFirstAndLastNames(ada);
        for (Author each:results) {
            each.setAge(90);
            authorService.update(each);
            assertThat(each.getAge()).isEqualTo(90);
        }
    }

    @Test
    public void shouldUpdateAuthorDob() {
        Author ada = new Author();
        ada.setFirstName("Ada");
        ada.setLastName("Lovelace");
        Collection<Author> results = authorService.findByFirstAndLastNames(ada);
        for (Author each:results) {
            each.setDob(Date.valueOf("2017-03-10"));
            authorService.update(each);
            assertThat(each.getDob().getTime()).isEqualTo(Date.valueOf("2017-03-10").getTime());
        }
    }

    @Test
    public void shouldDeleteAuthor() {
        Author ada = new Author();
        ada.setFirstName("Ada");
        ada.setLastName("Lovelace");
        authorService.deleteByFirstAndLastName(ada);
        Collection<Author> results = authorService.findByFirstAndLastNames(ada);
        assertThat(results.size());
    }

    @Test
    public void shouldNotAddAuthorWithEmptyFirstName() {
        exception.expect(TransactionException.class);
        Author a = new Author();
        a.setFirstName("");
        authorService.add(a);
    }

    @Test
    public void shouldNotUpdateAuthorWithEmptyFirstName() {
        exception.expect(TransactionException.class);
        Author a = new Author();
        a.setFirstName("");
        authorService.update(a);
    }
}