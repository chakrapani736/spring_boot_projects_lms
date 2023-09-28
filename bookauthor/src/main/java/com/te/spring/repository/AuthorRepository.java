package com.te.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.spring.model.Author;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
