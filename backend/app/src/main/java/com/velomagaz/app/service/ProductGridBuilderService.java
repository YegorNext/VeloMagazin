package com.velomagaz.app.service;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velomagaz.app.repository.*;
import com.velomagaz.app.service.component.*;
import com.velomagaz.app.entity.*;

@Service
public class ProductGridBuilderService {
	@Autowired
	private IProductRepository productRepository; 
	private final int ROW_SIZE = 4;
	
	private List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public LinkedList<ProductRow> BuildGrid() {
		LinkedList<ProductRow> productGrid = new LinkedList<>();
		ListIterator<Product> productIterator = getAllProducts().listIterator();		
		
		while(productIterator.hasNext()) {
			productGrid.add(new ProductRow());
		
			for(int j = 0; j < ROW_SIZE && productIterator.hasNext(); j++) {
				productGrid.getLast().AddElement(productIterator.next());
			}
		}
		
		return productGrid;
	}
	
}
