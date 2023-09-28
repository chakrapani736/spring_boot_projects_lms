
package com.te.business.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product_info")
public class Product {
	@Id
	private int productId;
	private String productName;
	private String productDescription;
	private double productPrice;
	@JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "productId"))
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Category> category;
}
