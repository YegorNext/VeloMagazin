package com.velomagaz.data_integration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.velomagaz.data_integration.entity.*;
import com.velomagaz.data_integration.entity.repository.*;

@Component
public class ProductComponentBuilder implements IProductComponentBuilder{
	
	@Autowired
	private IComponentRepository componentRepository;
	
	@Autowired
	private IProductRepository productRepository;
	
	private List<ProductComponent> productComponents;
	
	public ProductComponentBuilder() {
		productComponents = new ArrayList<ProductComponent>();
	}
	
	public List<ProductComponent> build(Map.Entry<String, Map<String, String>> productComponent){
		if(productComponent == null) {
			System.err.println("WARN<ProductComponentBuilder>: productComponent list is null");
			return null;
		}
		
		String id = productComponent.getKey();
		
		if(!id.isEmpty()) fillProductComponent(id, productComponent.getValue());

		
		return productComponents.isEmpty() ? null : productComponents;
	}
		
	private void fillProductComponent(String id, Map<String, String> components) {
		for(Map.Entry<String, String> component : components.entrySet()) {
			if(!component.getKey().isEmpty() && !component.getValue().isEmpty()) {
				productComponents.add(makeProductComponent(id, component));
			}
		}
	}
	
	private ProductComponent makeProductComponent(String id, Map.Entry<String, String> component) {
		ProductComponent productComponent = new ProductComponent();
		
		
		Product item = productRepository.findProductById(id);
		if(item == null) {
			System.err.println("WARN<ProductComponentBuilder>: can't find product with id " + id);
			return null;
		}
		
		productComponent.setProductId(item);
		productComponent.setDescValue(component.getValue());
		productComponent.setComponent(componentRepository.findByComponentName(component.getKey()));

		return productComponent;
	}
}
