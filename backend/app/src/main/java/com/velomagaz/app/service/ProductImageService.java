package com.velomagaz.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.velomagaz.app.repository.*;
import com.velomagaz.app.entity.Product;


@Service
public class ProductImageService {

	@Autowired
	private IProductRepository productService;
	
	public byte[] getImageById(String id) {
		Optional<Product> productOptional = productService.findById(id);
		
        return productOptional.map(Product::getImage).orElse(null);
	}
	

}
