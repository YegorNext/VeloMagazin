package com.velomagaz.app.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.velomagaz.app.ViewModel.CartItemViewModel;
import com.velomagaz.app.entity.Product;
import com.velomagaz.app.repository.IProductRepository;

@Component
public class CartItemViewModelBuilder {
	
	@Autowired
	private IProductRepository productRepository;
	
	public CartItemViewModel build(String id, List<String> items) {
		
		
		return makeViewModel(findProduct(id), id, items);
	}
	
	private Product findProduct(String id) {
		return productRepository.findById(id).orElse(null);
	}
	
	private CartItemViewModel makeViewModel(Product product, String id, List<String> items) {
		return (product == null) ? null : new CartItemViewModel(product.getProductName(), product.getPrice(), product.getImage(), Collections.frequency(items, id));
	}
}
