package com.velomagaz.app.ViewModel;

import java.math.BigDecimal;

public class ProductViewModel {
	private String id;
	private String productName;
	private BigDecimal price;
	private byte[] image;
	
	// -- Constructor
	
	public ProductViewModel(String id, String productName, BigDecimal price, byte[] image) {
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.image = image;
	}
	
	// -- Getters
	public String getId() { return id; }
	public String getProductName() { return productName; }
	public BigDecimal getPrice() { return price; }
	public byte[] getImage() { return image; }  
	
}
