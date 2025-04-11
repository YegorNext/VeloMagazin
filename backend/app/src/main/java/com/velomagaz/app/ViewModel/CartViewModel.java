package com.velomagaz.app.ViewModel;

import java.util.List;

public class CartViewModel {
	private List<CartItemViewModel> cartItems;
	private int itemCount;
	
	public CartViewModel(List<CartItemViewModel> cartItems, int itemCount) {
		this.cartItems = cartItems;
		this.itemCount = itemCount;
	}
	
	public CartViewModel() {}
	
	public List<CartItemViewModel> getCartItems() {
		return cartItems;
	}
	
	public int getItemCount() {
		return itemCount;
	}
}
