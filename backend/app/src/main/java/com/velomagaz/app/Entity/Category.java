package com.velomagaz.app.Entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="category")
public class Category {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="category_name", nullable=false, unique=true)
	private String categoryName;
	
	// -- Navigation --
	@OneToMany(mappedBy="categoryId")
	private List<Product> products;
	
	@OneToMany(mappedBy="categoryId")
	private List<Component> components; 

	// -- Setters and Getters --
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getCategoryName() { return categoryName; }
	public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
	
	public List<Product> getProducts() { return products; }
	public void setProducts(List<Product> products) { this.products = products; };
	
	public List<Component> getComponents() { return components; }
	public void setComponents(List<Component> components) { this.components = components; } 
	
}
