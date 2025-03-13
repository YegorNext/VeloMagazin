package com.velomagaz.data_integration;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ComponentFormatter implements IComponentFormatter{
	public List<String> format(Map<String, Map<String, String>> productComponents) {
		if(productComponents == null || productComponents.isEmpty()) {
			System.err.println("WARN<ComponentFormatter>: productComponent list is empty");
			return null;
		};
		
		List<String> components = new ArrayList<String>();
	
		for(Map.Entry<String, Map<String, String>> data : productComponents.entrySet()) {
			makeList(components, data);
		}
		
		return components.isEmpty() ? null : components;
	}
	
	private void makeList(List<String> components, Map.Entry<String, Map<String, String>> data) {
		for(Map.Entry<String, String> component : data.getValue().entrySet()) {
			components.add(component.getKey());
		}
	}
}
