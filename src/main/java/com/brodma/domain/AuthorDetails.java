package com.brodma.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(catalog = "publishing", schema = "public", name = "authorDetails")
public class AuthorDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Type(type = "objectid")
    private String id;

    private int weight;

    private int height;

    private String nationality;

    private String born;

    @OneToOne(cascade = CascadeType.PERSIST)
    @PrimaryKeyJoinColumn
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @MapsId
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDetails that = (AuthorDetails) o;
        return weight == that.weight &&
                height == that.height &&
                Objects.equals(nationality, that.nationality) &&
                Objects.equals(born, that.born) &&
                Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, height, nationality, born, author);
    }

    @Override
    public String toString() {
        return "AuthorDetails{id=" + Objects.toString(id)
                + ", weight=" + Objects.toString(weight)
                + ", height=" + Objects.toString(height)
                + ", nationality=" + Objects.toString(nationality)
                + ", born=" + Objects.toString(born)
                + ", author=" + Objects.toString(author)
                + "}";
    }
}
