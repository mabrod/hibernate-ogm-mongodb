package com.brodma.dao;

import com.brodma.domain.Review;
import java.util.Collection;

public interface ReviewRepo {

    void add(Review review);

    void update(Review review);

    void delete(Review review);

    void deleteByReview(String review);

    Collection<Review> findByReview(String review);

    Collection<Review> findByBookId(String bookId);

}
