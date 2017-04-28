package com.brodma.util;

import com.brodma.domain.Book;
import com.brodma.domain.Review;
import com.brodma.service.BookService;
import com.brodma.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Optional;

@Component
public class ReviewScenarios implements ExecuteStrategy {

    private static final Logger LOG = LogManager.getLogger(ReviewScenarios.class);

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;

    @Override
    public void execute() {
        LOG.info("Executing Review scenarios...");
		Optional<Book> book =  bookService.findByIsbn("528-3-15-148412-0");
		if (book.isPresent()) {
            Collection<Review> reviews = reviewService.findByBookId(book.get().getId());
            reviews.stream().forEach(LOG::info);
            Review review = new Review();
            review.setReview("Some comment about a book.");
            review.setBook(book.get());
            reviewService.add(review);
        }
    }
}
