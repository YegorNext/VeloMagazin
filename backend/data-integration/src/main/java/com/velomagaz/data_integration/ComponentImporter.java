package com.velomagaz.data_integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velomagaz.data_integration.entity.*;
import com.velomagaz.data_integration.entity.repository.*;

@Service
public class ComponentImporter implements IComponentImporter{
	
	@Autowired
	private IComponentRepository componentRepository;
	
	public void importComponentList(List<String> components) {
		if(components == null || components.isEmpty()) {
			System.out.println("WARN: Components list is empty");
			return;
		}
		
		for(String component : components) {
			if(isComponentExists(component)) continue;
			createComponent(component);
		}
	}
	
	private boolean isComponentExists(String componentName) {
		return componentRepository.findByComponentName(componentName) != null;
	}
	
	private void createComponent(String componentName) {
		Component item = new Component();
		item.setComponentName(componentName);
		
		componentRepository.save(item);
	}
}
