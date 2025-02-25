package com.velomagaz.app.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.velomagaz.app.repository.*;
import com.velomagaz.app.ViewModel.ProductInfoViewModel;
import com.velomagaz.app.entity.*;

@Service
public class ProductInfoBuilder {
	
	@Autowired
	private IProductComponentRepository productComponentRepository;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private IComponentRepository componentRepository;
	
	public ProductInfoViewModel BuildInfo(String id) {
		Product element = productRepository.findById(id).orElse(null);

		List<ProductComponent> productComponents = productComponentRepository.findByProductId(element);

		return new ProductInfoViewModel(id, element.getProductName(), createList(productComponents), element.getDescription(), element.getPrice());
	}
	
	private List<Map.Entry<String, String>> createList(List<ProductComponent> productComponents) {
		
		List<Map.Entry<String, String>> list = new LinkedList<>();
		
		for(ProductComponent productComponent : productComponents) {
			String key = componentRepository.findById(productComponent.getComponentId().getId())
                    .map(Component::getComponentName)
                    .orElse(null);
			
			list.add(Map.entry(key, productComponent.getDescValue()));
		}
		
		return list;
		
	}

}
