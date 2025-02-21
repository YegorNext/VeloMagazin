package com.velomagaz.app.service.component;

import java.util.LinkedList;
import java.util.List;

import com.velomagaz.app.entity.*;
import com.velomagaz.app.ViewModel.*;


public class ProductRow {
	private List<ProductViewModel> elements;
	
	public ProductRow() {
		elements = new LinkedList<>();
	}
	
	public int GetRowSize() {
		return elements.size();
	}
	
	public List<ProductViewModel> GetRowList() {
		return elements;
	}
	
	public void AddElement(Product product) {
		elements.add(buildViewModel(product));
	}
	
	private ProductViewModel buildViewModel(Product product) { 
		return new ProductViewModel(product.getId(), product.getProductName(), product.getPrice(), product.getImage());
	}
}
