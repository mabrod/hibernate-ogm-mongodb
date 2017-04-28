package com.brodma.domain;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(catalog = "publishing", schema = "public", name = "books")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Type(type = "objectid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank(message = "ISBN can not be null or empty")
    @Column(name="isbn")
    private String isbn;

    @NotBlank(message = "Title can not be null or empty")
    @Column(name="title")
    private String title;

    @ManyToMany( mappedBy = "books" )
    private Set<Author> authors = new HashSet<>();

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn) &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title);
    }

    @Override
    public String toString() {
        return "Book{id=" + Objects.toString(id)
                + ", isbn=" + Objects.toString(isbn)
                + ", title=" + Objects.toString(title)
                + "}";
    }
}
