package com.brodma.dao;

import com.brodma.domain.AuthorDetails;

public interface AuthorDetailsRepo {

    void add(AuthorDetails authorDetails);

    void update(AuthorDetails authorDetails);

    void delete(AuthorDetails authorDetails);
}
