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
	
	public LinkedList<ProductRow> buildGrid(List<Product> products) {
		ListIterator<Product> productIterator = (products == null) ? null : products.listIterator();	
		
		return (productIterator == null) ? null : fillGrid(productIterator);
	}
	
	public LinkedList<ProductRow> buildPageableGrid(Page<Product> productPage){
		return (productPage == null || productPage.isEmpty()) ? null : fillGrid(productPage.getContent().listIterator());
	}
	
}
