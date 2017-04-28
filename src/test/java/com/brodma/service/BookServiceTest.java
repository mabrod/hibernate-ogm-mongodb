package com.brodma.service;

import com.brodma.domain.Book;
import com.brodma.util.conf.MongoTestConfig;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionException;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MongoTestConfig.class}, webEnvironment=NONE)
@ActiveProfiles({"test"})
public class BookServiceTest {

    private static final String ISBN_1 = "928-3-15-148413-0";
    private static final String ISBN_2 = "908-3-15-148410-0";
    private static final String ISBN_3 = "978-3-16-148810-0";
    private static final String BOOK_TITLE = "Some book title";


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private BookService bookService;

    @Before
    public void setUp() {
        Book book1 = new Book();
        book1.setTitle(BOOK_TITLE);
        book1.setIsbn(ISBN_1);
        Book book2 = new Book();
        book2.setTitle(BOOK_TITLE);
        book2.setIsbn(ISBN_2);
        bookService.add(book1);
        bookService.add(book2);
    }

    @Test
    public void shouldNotPersistBookWithEmptyISBN() {
        exception.expect(TransactionException.class);
        Book book = new Book();
        book.setTitle(BOOK_TITLE);
        book.setIsbn("");
        bookService.add(book);
    }

    @Test
    public void shouldNotPersistBookWithMissingISBN() {
        exception.expect(TransactionException.class);
        Book book = new Book();
        book.setTitle(BOOK_TITLE);
        bookService.add(book);
    }

    @Test
    public void shouldNotFindBookWithISBNInCollection() {
        Optional<Book> result = bookService.findByIsbn(ISBN_3);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void shoulFindBookWithISBNInCollection() {
        Optional<Book> result = bookService.findByIsbn(ISBN_2);
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getIsbn()).isEqualTo(ISBN_2);
    }
}