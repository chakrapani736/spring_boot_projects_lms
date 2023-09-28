package com.te.business.service;

import java.util.List;

import com.te.business.dto.ProductDTO;
import com.te.business.exception.EntityNotFoundException1;
import com.te.business.exception.InvalidInputException;
import com.te.business.model.Product;


public interface ProductCategoryService {

	Product saveData(ProductDTO dto)throws EntityNotFoundException1;

	Product findDataById(int id)throws InvalidInputException;

	Product updateData(ProductDTO dto,int id)throws InvalidInputException;

	Product deleteData(int id)throws InvalidInputException;

	List<ProductDTO> findAllData()throws InvalidInputException;

}
