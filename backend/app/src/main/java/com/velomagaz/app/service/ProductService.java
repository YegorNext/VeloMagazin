package com.velomagaz.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.velomagaz.app.entity.Product;
import com.velomagaz.app.repository.IProductRepository;

@Service
public class ProductService {
	@Autowired
	IProductRepository productRepository;
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	} 
	
	public Page<Product> getPagebaleProducts(int page, int size){

		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());		
		Page<Product> productPage = productRepository.findAll(pageable);
			
		return (productPage == null || productPage.isEmpty()) ? null : productPage;
	}
	
	public int getTotalPages(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());		
		Page<Product> productPage = productRepository.findAll(pageable);
		
		return productPage.getTotalPages();
	}
}
