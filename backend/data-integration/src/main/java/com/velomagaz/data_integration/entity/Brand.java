package com.velomagaz.data_integration.entity;

import java.sql.Clob;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="brand")
public class Brand {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="brand_name", length=50, unique=true)
	private String brandName;
	
	@Column(name="description")
	private Clob description;
	
	// -- Setters and Getters -- 
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getBrandName() { return brandName; }
	public void setBrandName(String brandName) { this.brandName = brandName; }
	
	public Clob getDescription() { return description; }
	public void setDescription(Clob description) { this.description = description; }
	
	// -- Navigation --
	@OneToMany(mappedBy="brand")
	private List<Product> products;
	
}
