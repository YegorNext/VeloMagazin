package com.velomagaz.app.ViewModel;

import java.math.BigDecimal;
import java.sql.Clob;
import java.util.List;
import java.util.Map;

public class ProductInfoViewModel {

	private String id;
	private String name;
	private BigDecimal price;
	private List<Map.Entry<String, String>> records;
	private Clob description;
	
	public ProductInfoViewModel(String id, String name, List<Map.Entry<String, String>> records, Clob description, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.records = records;
		this.description = description;
		this.price = price;
	}
	
	// -- Getters
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public List<Map.Entry<String, String>> getRecords(){
		return records;
	}
	
	public Clob getDescription() {
		return description;
	}
	
}
