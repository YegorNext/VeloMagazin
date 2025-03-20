package com.velomagaz.app.service;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.velomagaz.app.repository.*;
import com.velomagaz.app.service.component.*;
import com.velomagaz.app.entity.*;

@Service
public class ProductGridBuilderService {
	@Autowired
	private ProductService productService; 
	
	private final int row_size = 4;
	
	private LinkedList<ProductRow> fillGrid(ListIterator<Product> productIterator){
		LinkedList<ProductRow> productGrid = new LinkedList<>();
		
		
		while(productIterator.hasNext()) {
			productGrid.add(new ProductRow());
		
			for(int j = 0; j < row_size && productIterator.hasNext(); j++) {
				productGrid.getLast().AddElement(productIterator.next());
			}
		}
		
		return productGrid;
	}
	
	public LinkedList<ProductRow> buildGrid() {
		ListIterator<Product> productIterator = productService.getAllProducts().listIterator();		
		
		return (productIterator == null) ? null : fillGrid(productIterator);
	}
	
	public LinkedList<ProductRow> buildPageableGrid(int page, int size){
		Page<Product> productPage = productService.getPagebaleProducts(page, size);
		
		return (productPage == null || productPage.isEmpty()) ? null : fillGrid(productPage.getContent().listIterator());
	}
	
	public LinkedList<ProductRow> buildPageableGridByQuery(int page, int size, String query){
		Page<Product> productPage = productService.getPagebaleProductsByQuery(query, page, size);
		
		return (productPage == null || productPage.isEmpty()) ? null : fillGrid(productPage.getContent().listIterator());
	}
	
	public LinkedList<ProductRow> buildPageableGridByCategoryName(int page, int size, String categoryName){
		Page<Product> productPage = productService.getPagebaleProductsByCategory(categoryName, page, size);
		
		return (productPage == null || productPage.isEmpty()) ? null : fillGrid(productPage.getContent().listIterator());
	}
}
