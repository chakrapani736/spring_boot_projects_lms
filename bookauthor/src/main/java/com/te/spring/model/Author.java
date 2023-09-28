package com.te.spring.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class Author {
	@Id
	private int authorId;
	private String authorName;
	private int authorAge;
	@OneToOne(cascade = CascadeType.ALL)
	private Book book;
	//@ManyToMany(cascade = CascadeType.ALL)
	//private List<Book>liBooks;

}
