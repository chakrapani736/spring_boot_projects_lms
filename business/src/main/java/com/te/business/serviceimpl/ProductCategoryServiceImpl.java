package com.te.business.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.business.dto.ProductDTO;
import com.te.business.exception.EntityNotFoundException1;
import com.te.business.exception.InvalidInputException;
import com.te.business.model.Product;
import com.te.business.repository.ProductRepository;
import com.te.business.service.ProductCategoryService;


@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductRepository repository;

	// creating product
	@Override
	public Product saveData(ProductDTO dto) throws EntityNotFoundException1 {
		Product product = new Product();
		BeanUtils.copyProperties(dto, product);
		if (product != null) {
			repository.save(product);
			return product;
		} else {
			throw new EntityNotFoundException1("data not present");
		}
	}

	// finding data by Id
	@Override
	public Product findDataById(int id) throws InvalidInputException {
		Product product = repository.findById(id).orElseThrow(() -> new InvalidInputException("invalid data"));
		return product;
	}

	// update data by Id
	@Override
	public Product updateData(ProductDTO dto, int id) throws InvalidInputException {
		Product orElseThrow = repository.findById(id).orElseThrow(() -> new InvalidInputException("invalid"));
		BeanUtils.copyProperties(dto, orElseThrow);
		return orElseThrow;
	}

	// delete data by Id
	@Override
	public Product deleteData(int id) throws InvalidInputException {
		Product product = repository.findById(id).orElseThrow(() -> new InvalidInputException("invalid"));
		Product product1 = new Product();
		BeanUtils.copyProperties(product, product1);
		repository.delete(product);
		return product1;
	}

	// find all the data from two tables
	@Override
	public List<ProductDTO> findAllData() throws InvalidInputException {
		List<Product> findAll = repository.findAll();
		List<ProductDTO> list = new ArrayList<>();
		if (findAll != null) {
			findAll.forEach(e -> {
				ProductDTO dto = new ProductDTO();
				dto.setProductId(e.getProductId());
				dto.setProductName(e.getProductName());
				dto.setProductDescription(e.getProductDescription());
				dto.setProductPrice(e.getProductPrice());
				dto.setCategory(e.getCategory());
				list.add(dto);
			});
			return list;
		} else {
			throw new InvalidInputException("invalid data");
		}

	}

}
