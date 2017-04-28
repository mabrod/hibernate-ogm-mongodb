package com.brodma.service;

import com.brodma.domain.Author;
import com.brodma.domain.AuthorDetails;
import com.brodma.util.conf.MongoTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.Date;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootTest(classes = {MongoTestConfig.class}, webEnvironment=NONE)
@ActiveProfiles({"test"})
public class AuthorDetailsServiceTest {

    @Autowired
    private AuthorDetailsService authorDetailsService;

    @Test
    public void shouldAddAuthorDetailsAndSharePrimaryKey() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setAge(1);
        author.setDob(Date.valueOf("2016-02-05"));

        AuthorDetails authorDetails = new AuthorDetails();
        authorDetails.setHeight(30);
        authorDetails.setWeight(10);
        authorDetails.setNationality("American");
        authorDetails.setBorn("NYC");
        authorDetails.setAuthor(author);
        authorDetailsService.add(authorDetails);

        assertThat(author.getId().equals(authorDetails.getId())).isTrue();
    }
}