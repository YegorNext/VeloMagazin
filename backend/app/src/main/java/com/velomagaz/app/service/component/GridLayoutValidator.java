package com.velomagaz.app.service.component;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.velomagaz.app.repository.IProductRepository;
import com.velomagaz.app.repository.ISubCategoryRepository;

@Component
public class GridLayoutValidator {
	@Autowired
	ISubCategoryRepository subCategoryRepository;
	
	@Autowired
	IProductRepository productRepository;
	
    public boolean isProductGridNullOrEmpty(LinkedList<ProductRow> productRow) {
    	return productRow == null || productRow.isEmpty();
    }
    
    public boolean isCategoryNullOrEmpty(String categoryName) {
    	return categoryName == null || categoryName.isEmpty() || subCategoryRepository.findBySubcategoryName(categoryName) == null;
    }
    
    public boolean isProductExists(String id) {
    	return productRepository.findById(id).orElse(null) != null;
    }
   
}
