package com.brodma.util;

import com.brodma.domain.Book;
import com.brodma.domain.Review;
import com.brodma.service.BookService;
import com.brodma.service.ReviewService;
import com.brodma.util.featureflag.FeatureFlags;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Optional;

@Component
public class ReviewScenarios implements ExecuteStrategy {

    @Autowired
    private Logger logger;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;

    @Override
    public void execute() {
        if (FeatureFlags.REVIEW_SCENARIOS.isActive()) {
            logger.info("Executing Review scenarios...");
            Optional<Book> book = bookService.findByIsbn("528-3-15-148412-0");
            if (book.isPresent()) {
                Collection<Review> reviews = reviewService.findByBookId(book.get().getId());
                reviews.stream().forEach(logger::info);
                Review review = new Review();
                review.setReview("Some comment about a book.");
                review.setBook(book.get());
                reviewService.add(review);
            }
        }
    }
}
