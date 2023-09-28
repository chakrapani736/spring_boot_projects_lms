 package com.te.spring.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Book {
	@Id
	private int bookId;
	private String bookName;
	private double bookCost;
	// OneToOne mapping
	@OneToOne(cascade = CascadeType.ALL)
	//@OneToOne(cascade = CascadeType.ALL)
	 private Author author;

	// OneToMany mapping
	 //@OneToMany
	// @JoinColumn(name = "li_author_author_id" , referencedColumnName = )
	// private List<Author>manyAuthor;

	// ManyToOneMapping
	// @ManyToOne(cascade = CascadeType.ALL)
	// private Author oneAuthor;

	// ManyToMany Mapping
	//@ManyToMany(cascade = CascadeType.ALL)
	//private List<Author> liAuthor;
}
