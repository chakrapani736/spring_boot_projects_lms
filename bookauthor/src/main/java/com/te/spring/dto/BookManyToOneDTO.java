package com.te.spring.dto;


import com.te.spring.model.Author;

import lombok.Data;
@Data
public class BookManyToOneDTO {
	private int bookId;
	private String bookName;
	private double bookCost;
	private Author author;
}
