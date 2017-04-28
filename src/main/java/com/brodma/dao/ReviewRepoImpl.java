package com.brodma.dao;

import com.brodma.domain.Review;
import org.hibernate.ogm.OgmSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class ReviewRepoImpl implements ReviewRepo {

    @Autowired
    @PersistenceContext(unitName="ogm-mongodb")
    private EntityManager entityManager;

    @Override
    public void add(Review review) {
        entityManager.persist(review);
    }

    @Override
    public void update(Review review) {
         entityManager.merge(review);
    }

    @Override
    public void delete(Review review) {
         entityManager.remove(review);
    }

    @Override
    public void deleteByReview(String review) {
        Collection<Review> toDelete = findByReview(review);
        for (Review each:toDelete) {
            entityManager.remove(each);
        }
    }

    @Override
    public Collection<Review> findByReview(String review) {
        Query query = entityManager.createQuery("FROM Review r WHERE r.review = :review");
        query.setParameter("review", review);
        return query.getResultList();
    }

    @Override
    public Collection<Review> findByBookId(String bookId) {
        String q = "{ $query : { book_id : { $eq:ObjectId(':bookId') }}}";
        OgmSession session = entityManager.unwrap(OgmSession.class);
        String queryString = session.createNativeQuery(q).getQueryString();
        queryString = queryString.replace( ":bookId", bookId );
        Query query = entityManager.createNativeQuery( queryString, Review.class );
        return query.getResultList();
    }
}
