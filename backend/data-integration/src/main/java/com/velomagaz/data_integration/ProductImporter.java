package com.velomagaz.data_integration;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.velomagaz.data_integration.entity.repository.IProductRepository;

@Service
public class ProductImporter {
	
	@Autowired
	private IProductRepository productRepository;
	
	@Autowired 
	private IProductFactory productFactory;
	
	@Autowired
	private IImageImporter imageImporter;
	
	public void importData(List<List<String>> dataSet, Map<String, String> images) {
		fillData(dataSet);
		imageImporter.importData(images);
		
	}
	
	private void fillData(List<List<String>> dataSet) {
		for(List<String> row : dataSet) {
			try {
				productRepository.save(productFactory.build(row));
			}catch(Exception e) {
				System.err.println("Cannot save product entity: " + e.getMessage());
			}
		}
	}
}
