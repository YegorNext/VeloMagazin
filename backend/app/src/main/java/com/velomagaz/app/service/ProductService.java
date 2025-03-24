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

		Pageable pageable = getPageable(page, size);
		Page<Product> productPage = productRepository.findAll(pageable);
			
		return (productPage == null || productPage.isEmpty()) ? null : productPage;
	}
	
/*	public int getTotalPages(int page, int size) {
		Pageable pageable = getPageRequestByIdDescending(page, size);	
		Page<Product> productPage = productRepository.findAll(pageable);
		
		return productPage.getTotalPages();
	}
	
	public int getTotalPagesByQuery(int page, int size, String query) {
		Pageable pageable = getPageRequestByIdDescending(page, size);		
		
		Page<Product> productPage = productRepository.findByNameContaining(query, pageable);			
		if(productPage.isEmpty()) productPage = productRepository.findByIdContaining(query, pageable);
		
		return productPage.getTotalPages();
	}
	
	public int getTotalPagesByCategoryName(int page, int size, String categoryName) {
		Pageable pageable = getPageRequestByIdDescending(page, size);		
		
		Page<Product> productPage = productRepository.findBySubcategory_SubcategoryName(categoryName, pageable);			
		
		return productPage.getTotalPages();
	} */
	
	public Page<Product> getPagebaleProductsByQuery(String query, int page, int size){
		if(query == null || query.isEmpty()) return null;
		
		Pageable pageable = getPageable(page, size);
		Page<Product> productPage = productRepository.findByNameContaining(query, pageable);
		
		if(productPage == null || productPage.isEmpty()) productPage = productRepository.findByIdContaining(query, pageable);
		
		return (productPage == null || productPage.isEmpty()) ? null : productPage;
	}
	
	public Page<Product> getPagebaleProductsByCategory(String categoryName, int page, int size){
		if(categoryName == null || categoryName.isEmpty()) return null;
		
		Pageable pageable = getPageable(page, size);
		Page<Product> productPage = productRepository.findBySubcategory_SubcategoryName(categoryName, pageable);
		
		return (productPage == null || productPage.isEmpty()) ? null : productPage;
	}
	
	private Pageable getPageable(int page, int size) {
		return PageRequest.of(page, size, Sort.by("id").descending());	
	}
}
