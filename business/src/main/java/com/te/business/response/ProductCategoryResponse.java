package com.te.business.response;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProductCategoryResponse {
	private boolean error;
	private int status;
	private String message;
	private Object liObjects;
}
