package com.brodma.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(catalog = "publishing", schema = "public", name = "authors")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Type(type = "objectid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank(message = "first name can not be null or empty")
    @Column(name="firstName")
    private String firstName;

    @NotBlank(message = "last name can not be null or empty")
    @Column(name="lastName")
    private String lastName;

    @Range(min = 1, max = 150, message="Age must be between 1 and 150")
    @Column(name = "age")
    private int age;

    @NotNull
    @Past(message="Date of Birth must be in the past or present")
    @Column(name = "dob")

    @Temporal(TemporalType.DATE)
    private Date dob;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Book> books = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDob() {
        return new Date(dob.getTime());
    }

    public void setDob(Date dob) {
        this.dob = new Date(dob.getTime());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return age == author.age &&
                Objects.equals(firstName, author.firstName) &&
                Objects.equals(lastName, author.lastName) &&
                Objects.equals(dob, author.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, dob);
    }

    @Override
    public String toString() {
        return "Author{id=" + Objects.toString(id)
                + ", firstName=" + Objects.toString(firstName)
                + ", lastName=" + Objects.toString(lastName)
                + ", firstName=" + Objects.toString(age)
                + ", dob=" + Objects.toString(dob)
                + "}";
    }
}
