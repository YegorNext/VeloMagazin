package com.velomagaz.data_integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velomagaz.data_integration.entity.*;
import com.velomagaz.data_integration.entity.repository.*;

@Service
public class BrandImporter {
	
	@Autowired
	private IBrandRepository brandRepository;
	
	public void importBrandList(List<String> brands) {
		for(String brand : brands) {
			if (isBrandExists(brand)) continue;
			createBrand(brand);
		}
	}
	
	private boolean isBrandExists(String brandName) {
		return brandRepository.findByBrandName(brandName) != null;
	}
	
	private void createBrand(String brandName) {
		Brand item = new Brand();
		item.setBrandName(brandName);
		
		brandRepository.save(item);
	}
}
