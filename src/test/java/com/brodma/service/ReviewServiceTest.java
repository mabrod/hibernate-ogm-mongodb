package com.brodma.service;

import com.brodma.domain.Author;
import com.brodma.domain.Book;
import com.brodma.domain.Review;
import com.brodma.util.conf.MongoTestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.Date;
import java.util.Collection;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MongoTestConfig.class}, webEnvironment=NONE)
@ActiveProfiles({"test"})
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Before
    public void setUp() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setAge(35);
        author.setDob(Date.valueOf("1989-02-05"));

        Book book = new Book();
        book.setTitle("Some title");
        book.setIsbn("528-4-16-148412-0");

        author.getBooks().add(book);
        book.getAuthors().add(author);

        authorService.add(author);
    }

    @Test
    public void shouldAddReviewToBook() {
        Optional<Book> book =  bookService.findByIsbn("528-4-16-148412-0");
        if (book.isPresent()) {
            Review review = new Review();
            review.setReview("This is a great book");
            review.setBook(book.get());
            reviewService.add(review);
            Collection<Review> reviews = reviewService.findByBookId(book.get().getId());
            assertThat(reviews).isNotEmpty();
            assertThat(reviews.contains(review)).isTrue();
        }
    }
}