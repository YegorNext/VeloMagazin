package com.velomagaz.data_integration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.velomagaz.data_integration.constant.IndexConstants;

@Component
public class ProductComponentFactory implements IProductComponentFactory{	
	public Map<String, Map<String, String>> build(List<String> data) {
		Map<String, Map<String, String>> productComponentValues = new HashMap<String, Map<String, String>>();
		
		Map<String, String> componentValues = parseJsonToMap(data.get(IndexConstants.INFO_IND));
		
		if(componentValues == null) return null;
		
		productComponentValues.put(data.get(IndexConstants.ID_IND), componentValues);
		
		return productComponentValues;
	}
	
	private Map<String, String> parseJsonToMap(String infoJSON) {
		
		try { 
			JSONObject obj = new JSONObject(infoJSON);
			
			if(obj.length() == 0) return null;
			
			return buildMap(obj.keys(), obj);
			
			
		}catch(JSONException e) {
			System.err.println("Error while creating JSON: " + e.getMessage());
		}
		
		return null;
	}
	
	private Map<String, String> buildMap(Iterator<String> keys, JSONObject obj) {
		Map<String, String> components = new HashMap<String, String>();
		
		while(keys.hasNext()) {
			String key = keys.next();
			components.put(key, obj.getString(key));
		}
		
		return components.isEmpty() ? null : components;
	}
	
}
