package com.te.spring.service;

import com.te.spring.dto.AuthorManyToManyDTO;
import com.te.spring.dto.AuthorOneToManyDTO;
import com.te.spring.dto.BookManyToManyDTO;
import com.te.spring.dto.BookManyToOneDTO;
import com.te.spring.dto.BookOneToManyDTO;
import com.te.spring.dto.BookOneToOneDTO;
import com.te.spring.exception.EntityNotFoundException1;

public interface BookAuthorService {
	
	//saving data for OneToOne mapping
	public BookOneToOneDTO saveOneToOneData(BookOneToOneDTO dto)throws EntityNotFoundException1;
	
	//saving data for OneToMany mapping
	public BookOneToManyDTO saveOneToManyData(BookOneToManyDTO dto)throws EntityNotFoundException1;

	//saving data for ManyToOne mapping
	public BookManyToOneDTO saveManyToOneData(BookManyToOneDTO dto)throws EntityNotFoundException1;

	//saving data for ManyToMany mapping
	public BookManyToManyDTO saveManyToManyData(BookManyToManyDTO dto)throws EntityNotFoundException1;
	
	//saving data for many author to many books
	public AuthorManyToManyDTO saveManyAuthorsToManyBooksData(AuthorManyToManyDTO dto)throws EntityNotFoundException1;
	
	//saving data for one author to many books
	public AuthorOneToManyDTO saveOneAuthorToManyBooksData(AuthorOneToManyDTO dto)throws EntityNotFoundException1;

}
