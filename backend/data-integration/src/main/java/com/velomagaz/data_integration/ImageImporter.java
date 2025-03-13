package com.velomagaz.data_integration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
			productRepository.updateImageById(image.getKey(), fileToBytes(image.getValue()));
		}
	}
	
	private byte[] fileToBytes(String path) {
		try {
			return Files.readAllBytes(Paths.get(path));
		}catch(IOException e) {
			System.err.println("Error while reading the file: " + e.getMessage());
			return null;
		}
	}
}
