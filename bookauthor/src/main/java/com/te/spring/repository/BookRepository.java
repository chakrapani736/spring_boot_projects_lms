package com.te.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.spring.model.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
