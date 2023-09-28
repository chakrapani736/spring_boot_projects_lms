package com.te.business.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.business.dto.ProductDTO;
import com.te.business.exception.EntityNotFoundException1;
import com.te.business.exception.InvalidInputException;
import com.te.business.model.Product;
import com.te.business.response.ProductCategoryResponse;
import com.te.business.service.ProductCategoryService;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class ProductCategoryController {
	@Autowired
	private ProductCategoryResponse response;
	@Autowired
	private ProductCategoryService service;

	// saving the data in database
	@PostMapping("save")
	public ResponseEntity<ProductCategoryResponse> saveData(@RequestBody ProductDTO dto) throws EntityNotFoundException1 {
		Product product = service.saveData(dto);
		if (product != null) {
			response.setError(false);
			response.setStatus(200);
			response.setMessage("data saved successfully");
			response.setLiObjects(Arrays.asList(product));
			return new ResponseEntity<ProductCategoryResponse>(response, HttpStatus.OK);
		} else {
			response.setError(true);
			response.setStatus(400);
			response.setMessage("something went wrong");
			response.setLiObjects(Arrays.asList(product));
			return new ResponseEntity<ProductCategoryResponse>(response, HttpStatus.BAD_GATEWAY);

		}
	}

	// retrieve by id
	@GetMapping("findById/{id}")
	public ResponseEntity<ProductCategoryResponse> findDataById(@PathVariable("id") int id) throws InvalidInputException {
		Product product = service.findDataById(id);
		if (product != null) {
			response.setError(false);
			response.setStatus(200);
			response.setMessage("data fetched successfully");
			response.setLiObjects(Arrays.asList(product));
			return new ResponseEntity<ProductCategoryResponse>(response, HttpStatus.OK);
		} else {
			response.setError(true);
			response.setStatus(400);
			response.setMessage("something went wrong");
			response.setLiObjects(Arrays.asList(product));
			return new ResponseEntity<ProductCategoryResponse>(response, HttpStatus.BAD_GATEWAY);

		}
	}

	// update details
	@PutMapping("update/{id}")
	public ResponseEntity<ProductCategoryResponse> updateData(@RequestBody ProductDTO dto, @PathVariable("id") int id)
			throws InvalidInputException {
		Product product = service.updateData(dto, id);
		if (product != null) {
			response.setError(false);
			response.setStatus(200);
			response.setMessage("data updated successfully");
			response.setLiObjects(Arrays.asList(product));
			return new ResponseEntity<ProductCategoryResponse>(response, HttpStatus.OK);
		} else {
			response.setError(true);
			response.setStatus(400);
			response.setMessage("something went wrong");
			response.setLiObjects(Arrays.asList(product));
			return new ResponseEntity<ProductCategoryResponse>(response, HttpStatus.BAD_GATEWAY);

		}
	}

	// delete product by id
	@DeleteMapping("deleteById/{id}")
	public ResponseEntity<ProductCategoryResponse> deleteData(@PathVariable("id") int id) throws InvalidInputException {
		Product product = service.deleteData(id);
		if (product != null) {
			response.setError(false);
			response.setStatus(200);
			response.setMessage("data deleted successfully");
			response.setLiObjects(Arrays.asList(product));
			return new ResponseEntity<ProductCategoryResponse>(response, HttpStatus.OK);
		} else {
			response.setError(true);
			response.setStatus(400);
			response.setMessage("something went wrong");
			response.setLiObjects(Arrays.asList(product));
			return new ResponseEntity<ProductCategoryResponse>(response, HttpStatus.BAD_GATEWAY);

		}
	}

	// retrieve list of objects
	@GetMapping("findAll")
	public ResponseEntity<ProductCategoryResponse> findAllData() throws InvalidInputException {
		List<ProductDTO> product = service.findAllData();
		if (product != null) {
			response.setError(false);
			response.setStatus(200);
			response.setMessage("data found successfully");
			response.setLiObjects(Arrays.asList(product));
			return new ResponseEntity<ProductCategoryResponse>(response, HttpStatus.OK);
		} else {
			response.setError(true);
			response.setStatus(400);
			response.setMessage("something went wrong");
			response.setLiObjects(Arrays.asList(product));
			return new ResponseEntity<ProductCategoryResponse>(response, HttpStatus.BAD_GATEWAY);

		}
	}

}
