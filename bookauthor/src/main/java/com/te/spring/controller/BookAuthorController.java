package com.te.spring.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.spring.dto.AuthorManyToManyDTO;
import com.te.spring.dto.AuthorOneToManyDTO;
import com.te.spring.dto.BookManyToManyDTO;
import com.te.spring.dto.BookManyToOneDTO;
import com.te.spring.dto.BookOneToManyDTO;
import com.te.spring.dto.BookOneToOneDTO;
import com.te.spring.exception.EntityNotFoundException1;
import com.te.spring.response.BookAuthorResponse;
import com.te.spring.service.BookAuthorService;

@RestController
public class BookAuthorController {
	@Autowired
	private BookAuthorService service;// has-a relationship
	@Autowired
	private BookAuthorResponse response;// has-a relationship

	// mapping for saving the data for one to one mapping
	@PostMapping("/saveOneToOne")
	public ResponseEntity<BookAuthorResponse> saveOneToOneData(@RequestBody BookOneToOneDTO dto)
			throws EntityNotFoundException1 {
		BookOneToOneDTO book = service.saveOneToOneData(dto);
		if (book != null) {
			response.setError(false);
			response.setStatus(200);
			response.setMessage("data saved successfully");
			response.setLiBooks(Arrays.asList(book));
			return new ResponseEntity<BookAuthorResponse>(response, HttpStatus.OK);
		} else {
			response.setError(true);
			response.setStatus(400);
			response.setMessage("something went wrong");
			response.setLiBooks(Arrays.asList(book));
			return new ResponseEntity<BookAuthorResponse>(response, HttpStatus.BAD_REQUEST);

		}
	}

	// mapping for saving the data for OneToMany
	@PostMapping("/saveOneToMany")
	public ResponseEntity<BookAuthorResponse> saveOneToManyData(@RequestBody BookOneToManyDTO dto)
			throws EntityNotFoundException1 {
		BookOneToManyDTO book = service.saveOneToManyData(dto);
		if (book != null) {
			response.setError(false);
			response.setStatus(200);
			response.setMessage("data saved successfully");
			response.setLiBooks(Arrays.asList(book));
			return new ResponseEntity<BookAuthorResponse>(response, HttpStatus.OK);
		} else {
			response.setError(true);
			response.setStatus(400);
			response.setMessage("something went wrong");
			response.setLiBooks(Arrays.asList(book));
			return new ResponseEntity<BookAuthorResponse>(response, HttpStatus.BAD_REQUEST);

		}
	}

	// mapping for saving the data for ManyToOne
	@PostMapping("/saveManyToOne")
	public ResponseEntity<BookAuthorResponse> saveManyToOneData(@RequestBody BookManyToOneDTO dto)
			throws EntityNotFoundException1 {
		BookManyToOneDTO book = service.saveManyToOneData(dto);
		if (book != null) {
			response.setError(false);
			response.setStatus(200);
			response.setMessage("data saved successfully");
			response.setLiBooks(Arrays.asList(book));
			return new ResponseEntity<BookAuthorResponse>(response, HttpStatus.OK);
		} else {
			response.setError(true);
			response.setStatus(400);
			response.setMessage("something went wrong");
			response.setLiBooks(Arrays.asList(book));
			return new ResponseEntity<BookAuthorResponse>(response, HttpStatus.BAD_REQUEST);

		}
	}

	// mapping for manyBooksToManyAuthors
	@PostMapping("/saveManyToMany")
	public ResponseEntity<BookAuthorResponse> saveManyToManyData(@RequestBody BookManyToManyDTO dto)
			throws EntityNotFoundException1 {
		BookManyToManyDTO book = service.saveManyToManyData(dto);
		if (book != null) {
			response.setError(false);
			response.setStatus(200);
			response.setMessage("data saved successfully");
			response.setLiBooks(Arrays.asList(book));
			return new ResponseEntity<BookAuthorResponse>(response, HttpStatus.OK);
		} else {
			response.setError(true);
			response.setStatus(400);
			response.setMessage("something went wrong");
			response.setLiBooks(Arrays.asList(book));
			return new ResponseEntity<BookAuthorResponse>(response, HttpStatus.BAD_REQUEST);

		}
	}
	// mapping for manyAuthorToManyBooks
	@PostMapping("/saveManyAuthorsToManyBooks")
	public ResponseEntity<BookAuthorResponse> saveManyAuthorsToManyBooksData(@RequestBody AuthorManyToManyDTO dto)
			throws EntityNotFoundException1 {
		AuthorManyToManyDTO book = service.saveManyAuthorsToManyBooksData(dto);
		if (book != null) {
			response.setError(false);
			response.setStatus(200);
			response.setMessage("data saved successfully");
			response.setLiBooks(Arrays.asList(book));
			return new ResponseEntity<BookAuthorResponse>(response, HttpStatus.OK);
		} else {
			response.setError(true);
			response.setStatus(400);
			response.setMessage("something went wrong");
			response.setLiBooks(Arrays.asList(book));
			return new ResponseEntity<BookAuthorResponse>(response, HttpStatus.BAD_REQUEST);
			
		}
	}
	// mapping for oneAuthorToManyBooks
	@PostMapping("/saveOneAuthorToManyBooks")
	public ResponseEntity<BookAuthorResponse> saveOneAuthorToManyBooksData(@RequestBody AuthorOneToManyDTO dto)
			throws EntityNotFoundException1 {
	AuthorOneToManyDTO book = service.saveOneAuthorToManyBooksData(dto);
		if (book != null) {
			response.setError(false);
			response.setStatus(200);
			response.setMessage("data saved successfully");
			response.setLiBooks(Arrays.asList(book));
			return new ResponseEntity<BookAuthorResponse>(response, HttpStatus.OK);
		} else {
			response.setError(true);
			response.setStatus(400);
			response.setMessage("something went wrong");
			response.setLiBooks(Arrays.asList(book));
			return new ResponseEntity<BookAuthorResponse>(response, HttpStatus.BAD_REQUEST);
			
		}
	}

}
