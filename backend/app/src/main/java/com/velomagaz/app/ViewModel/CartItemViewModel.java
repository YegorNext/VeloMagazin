package com.velomagaz.app.ViewModel;

import java.math.BigDecimal;

public class CartItemViewModel {
	private String productName;
	private BigDecimal price;
	private byte[] image;
	private int count;

	public CartItemViewModel(String productName, BigDecimal price, byte[] image, int count) {
		this.productName = productName;
		this.price = price;
		this.image = image;
		this.count = count;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public byte[] getImage() {
		return image;
	}
	
	public int getCount() {
		return count;
	}
}
