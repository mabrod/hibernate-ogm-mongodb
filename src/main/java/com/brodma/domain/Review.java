package com.brodma.domain;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(catalog = "publishing", schema = "public", name = "reviews")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Type(type = "objectid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank(message = "review can not be null or empty")
    @Column(name="review")
    private String review;

    /**
     * This is many to one unidirectional mapping
     */
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review1 = (Review) o;
        return Objects.equals(review, review1.review) &&
                Objects.equals(book, review1.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(review, book);
    }

    @Override
    public String toString() {
        return "Review{id=" + Objects.toString(id)
                + ", review=" + Objects.toString(review)
                + ", book=" + Objects.toString(book)
                + "}";
    }
}
