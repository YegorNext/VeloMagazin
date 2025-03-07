package com.velomagaz.data_integration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.velomagaz.data_integration.entity.repository.IProductRepository;

@Component
public class ImageImporter implements IImageImporter{
	
	@Autowired
	private IProductRepository productRepository;
	
	public void importData(Map<String, String> images) {
		
		for(Map.Entry<String, String> image : images.entrySet()) {
			productRepository.updateImageById(image.getKey(), image.getValue().getBytes());
		}
	}
}
