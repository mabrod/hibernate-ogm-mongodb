package com.brodma.service;

import com.brodma.dao.AuthorDetailsRepo;
import com.brodma.domain.AuthorDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorDetailsService {

    private AuthorDetailsRepo authorDetailsRepo;

    public AuthorDetailsService(AuthorDetailsRepo authorDetailsRepo) {
        this.authorDetailsRepo = authorDetailsRepo;
    }

    public void add(AuthorDetails authorDetails) {
       authorDetailsRepo.add(authorDetails);
    }

}
