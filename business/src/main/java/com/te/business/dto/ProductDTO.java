package com.te.business.dto;

import java.util.List;

import com.te.business.model.Category;

import lombok.Data;

@Data
public class ProductDTO {
	private int productId;
	private String productName;
	private String productDescription;
	private double productPrice;
	private List<Category> category;
}
