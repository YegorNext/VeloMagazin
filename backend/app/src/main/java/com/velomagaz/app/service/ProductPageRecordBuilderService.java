package com.velomagaz.app.service;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.velomagaz.app.entity.Product;
import com.velomagaz.app.service.record.ProductPageable;


@Service
public class ProductPageRecordBuilderService {

	@Autowired
	private ProductGridBuilderService gridBuilder;
	
	@Autowired
	private ProductService productService; 
	
	public ProductPageable buildPageable(int page, int size) {		
		return buildProductPageable(productService.getPagebaleProducts(page, size), page);
	}
	
	public ProductPageable buildPageableByQuery(String query, int page, int size) {
		return buildProductPageable(productService.getPagebaleProductsByQuery(query, page, size), page);
	}
	
	public ProductPageable buildPageableByCategory(String categoryName, int page, int size) {	
		return buildProductPageable(productService.getPagebaleProductsByCategory(categoryName, page, size), page);
	}
	
	private int getEndPage(int totalPages, int page) {
		return Math.min(page + 10, totalPages);
	}
	
	private ProductPageable buildProductPageable(Page<Product> productPage, int page) {
		if(productPage == null) return getEmptyProductPageable();
		
		int totalPages = productPage.getTotalPages();
		return new ProductPageable(totalPages, getEndPage(totalPages, page), gridBuilder.buildPageableGrid(productPage));
	}
	
	private ProductPageable getEmptyProductPageable() {
		return new ProductPageable(0, 0, new LinkedList<>());
	}
}
