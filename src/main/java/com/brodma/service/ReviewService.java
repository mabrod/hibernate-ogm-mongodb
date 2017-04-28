package com.brodma.service;

import com.brodma.dao.ReviewRepo;
import com.brodma.domain.Review;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

@Service
@Transactional
public class ReviewService {

    private ReviewRepo reviewRepo;

    public ReviewService(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    public void add(Review review) {
        reviewRepo.add(review);
    }

    public void update(Review review) {
        reviewRepo.update(review);
    }

    public void delete(Review review) {
        reviewRepo.delete(review);
    }

    @Transactional(readOnly = true)
    public Collection<Review> findByBookId(String bookId) {
        return reviewRepo.findByBookId(bookId);
    }

    @Transactional(readOnly = true)
    public Collection<Review> findByReview(String review) {
        return reviewRepo.findByReview(review);
    }
}
