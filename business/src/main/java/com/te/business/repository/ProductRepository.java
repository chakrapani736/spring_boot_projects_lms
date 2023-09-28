package com.te.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.business.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
