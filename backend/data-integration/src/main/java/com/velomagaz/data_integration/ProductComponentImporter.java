package com.velomagaz.data_integration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.velomagaz.data_integration.entity.repository.*;

import jakarta.transaction.Transactional;

import com.velomagaz.data_integration.entity.*;

@Service
public class ProductComponentImporter implements IProductComponentImporter{

	@Autowired
	private IProductComponentRepository productComponentRepository;
	
	@Autowired
	private IProductComponentFactory productComponentFactory;
	
	@Autowired
	private IComponentFormatter componentFormatter;
	
	@Autowired
	private IComponentImporter componentImporter;
	
	@Autowired 
	private IProductComponentBuilder productComponentBuilder;
	
	@Transactional
	public void importData(List<List<String>> dataSet) {
		
		int dataSetSize = dataSet.size();
		int rowCount = 0;
		
		for(List<String> row : dataSet) {
			Map<String, Map<String, String>> productComponents = productComponentFactory.build(row);
		
			if(productComponents == null || productComponents.isEmpty()) continue;
			
			componentImporter.importComponentList(componentFormatter.format(productComponents));
			fillData(productComponents);
			
			System.out.println("INFO: Components from row was imported, " + (rowCount++) + "/" + dataSetSize);
		}
	}
	
	
	private void fillData(Map<String, Map<String, String>> productComponents) {
		for(Map.Entry<String, Map<String, String>> productComponent : productComponents.entrySet()) {
			List<ProductComponent> elements = productComponentBuilder.build(productComponent);
			
			if(elements == null || elements.isEmpty()) continue;
			
			for(ProductComponent item : elements) {
				saveData(item);
			}
		}
	}
	
		
	private void saveData(ProductComponent item) {
		try {
			productComponentRepository.save(item);
		}catch(Exception e) {
			System.err.println("Error while saving product_component: " + e.getMessage());
		}
	}
}
