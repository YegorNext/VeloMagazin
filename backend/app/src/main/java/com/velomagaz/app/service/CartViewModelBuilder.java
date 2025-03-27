package com.velomagaz.app.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velomagaz.app.ViewModel.CartItemViewModel;
import com.velomagaz.app.ViewModel.CartViewModel;

@Service
public class CartViewModelBuilder {
	
	@Autowired
	private CartItemViewModelBuilder itemViewModelBuilder;
	
	public CartViewModel build(List<String> id) {
		List<CartItemViewModel> items = makeViewModel(id);
		
		return items == null ? null : new CartViewModel(items, items.size());
	}
	
	private List<CartItemViewModel> makeViewModel(List<String> id) {
		List<CartItemViewModel> itemModels = new ArrayList<CartItemViewModel>();
		
		for(String item : new LinkedHashSet<String>(id)) {
			CartItemViewModel itemViewModel = itemViewModelBuilder.build(item, id);
			if(itemViewModel != null) itemModels.add(itemViewModel);
		}
		
		return (itemModels == null || itemModels.isEmpty()) ? null : itemModels;
	}
}
