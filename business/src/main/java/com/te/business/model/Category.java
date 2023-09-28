package com.te.business.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "category_info")
public class Category {
	@Id
	private int categoryId;
	private String categoryName;
	private String categoryDescription;

}
