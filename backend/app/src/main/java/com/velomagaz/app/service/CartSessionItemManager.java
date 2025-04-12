package com.velomagaz.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CartSessionItemManager {
	public List<String> add(List<String> items, String id) {
		if(items == null) {
			items = new ArrayList<String>();
		}
		items.add(id);
		return items;
	}
	
	public List<String> delete(List<String> items, String id){
		if(items != null && items.contains(id)) {
			items.remove(id);
		}
		
		return items;
	}
	
	public List<String> deleteAll(List<String> items, String id){
		if(items != null && items.contains(id)) {
			items.removeAll(Collections.singleton(id));
		}
		
		return items;
	}
}
