package com.te.spring.dto;

import java.util.List;

import com.te.spring.model.Book;

import lombok.Data;
@Data
public class AuthorOneToManyDTO {
	private int authorId;
	private String authorName;
	private int authorAge;
	private List<Book>liBooks;
}
