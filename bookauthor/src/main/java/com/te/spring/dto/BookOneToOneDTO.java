package com.te.spring.dto;

import java.util.List;

import com.te.spring.model.Author;

import lombok.Data;

@Data
public class BookOneToOneDTO {
	private int bookId;
	private String bookName;
	private double bookCost;
	private Author author;
	
	
}
