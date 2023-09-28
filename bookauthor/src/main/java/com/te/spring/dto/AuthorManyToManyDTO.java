package com.te.spring.dto;

import lombok.Data;
import java.util.*;

import com.te.spring.model.Book;

@Data
public class AuthorManyToManyDTO {
	private int authorId;
	private String authorName;
	private int authorAge;
	private List<Book>liBooks;
}
