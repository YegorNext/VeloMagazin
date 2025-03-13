package com.velomagaz.data_integration;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velomagaz.data_integration.entity.Product;

import com.velomagaz.data_integration.entity.repository.IProductRepository;

@Service
public class ProductImporter implements IProductImporter{
	
	@Autowired
	private IProductRepository productRepository;
	
	@Autowired 
	private IProductFactory productFactory;
	
	@Autowired
	private IImageImporter imageImporter;
	
	public void importData(List<List<String>> dataSet, Map<String, String> imagesPath) {
		fillData(dataSet);
		imageImporter.importData(imagesPath);
		
	}
	
	private void fillData(List<List<String>> dataSet) {
		for(List<String> row : dataSet) {
			Product product = productFactory.build(row);
			
			
			
			// if(row.isEmpty()) { System.err.println("!ROW IS EMPTY!"); }
			
			if(product == null || product.getId().isEmpty()) {
				System.err.println("Cannot save product entity: product entity is null");
				continue;
			}
			
		
			if(productRepository.findProductById(product.getId()) != null) {
				System.err.println("Cannot save product entity: product with id '" + product.getId() +  "' already exists");
				continue;
			}
			
			try {
			//	System.out.println("Product id: " + product.getId() + ", Name: " + 
			//	product.getProductName() + ".");
				
			//	System.in.read();


				productRepository.save(product);
			}catch(Exception e) {
				System.err.println("Cannot save product entity: " + e.getMessage());
			}
		}
	}
}
