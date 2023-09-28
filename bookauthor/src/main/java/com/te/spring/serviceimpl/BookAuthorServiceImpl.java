package com.te.spring.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.spring.dto.AuthorManyToManyDTO;
import com.te.spring.dto.AuthorOneToManyDTO;
import com.te.spring.dto.BookManyToManyDTO;
import com.te.spring.dto.BookManyToOneDTO;
import com.te.spring.dto.BookOneToManyDTO;
import com.te.spring.dto.BookOneToOneDTO;
import com.te.spring.exception.EntityNotFoundException1;
import com.te.spring.model.*;
import com.te.spring.repository.AuthorRepository;
import com.te.spring.repository.BookRepository;
import com.te.spring.service.BookAuthorService;


@Service
public class BookAuthorServiceImpl implements BookAuthorService {
	@Autowired
	private BookRepository repository;//has-a relationship
	
	@Autowired
	private AuthorRepository repo;

	@Override
	public BookOneToOneDTO saveOneToOneData(BookOneToOneDTO dto) throws EntityNotFoundException1 {
		Book book = new Book();
		BeanUtils.copyProperties(dto, book);
		if (book != null) {
			repository.save(book);//this method will save data
			return dto;
		} else {
			//throwing exception
			throw new EntityNotFoundException1("data not present");
		}

	}

	@Override
	public BookOneToManyDTO saveOneToManyData(BookOneToManyDTO dto) throws EntityNotFoundException1{
		Book book = new Book();
		BeanUtils.copyProperties(dto, book);
		if (book != null) {
			repository.save(book);//this method will save data
			return dto;
		} else {
			//throwing exception
			throw new EntityNotFoundException1("data not present");
		}

	}

	@Override
	public BookManyToOneDTO saveManyToOneData(BookManyToOneDTO dto) throws EntityNotFoundException1{
		Book book = new Book();
		BeanUtils.copyProperties(dto, book);
		if (book != null) {
			repository.save(book);//this method will save data
			return dto;
		} else {
			//throwing exception
			throw new EntityNotFoundException1("data not present");
		}
	}

	@Override
	public BookManyToManyDTO saveManyToManyData(BookManyToManyDTO dto) throws EntityNotFoundException1{
		Book book = new Book();
		BeanUtils.copyProperties(dto, book);
		if (book != null) {
			repository.save(book);//this method will save data
			return dto;
		} else {
			//throwing exception
			throw new EntityNotFoundException1("data not present");
		}
	}

	@Override
	public AuthorManyToManyDTO saveManyAuthorsToManyBooksData(AuthorManyToManyDTO dto) throws EntityNotFoundException1{
		Author author = new Author();
		BeanUtils.copyProperties(dto, author);
		if(author!=null) {
			repo.save(author);
			return dto;
		}else {
			throw new EntityNotFoundException1("entity not found");
		}
		
		
	}

	@Override
	public AuthorOneToManyDTO saveOneAuthorToManyBooksData(AuthorOneToManyDTO dto) throws EntityNotFoundException1 {
		Author author = new Author();
		BeanUtils.copyProperties(dto, author);
		if(author!=null) {
			repo.save(author);
			return dto;
		}else {
			throw new EntityNotFoundException1("entity not found");
		}
		
	}

	

}
