package com.velomagaz.app.entity;

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
	@OneToMany(mappedBy="category")
	private List<SubCategory> subcategories;

	// -- Setters and Getters --
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getCategoryName() { return categoryName; }
	public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
	
	public List<SubCategory> getSubcategories() { return subcategories; }
	public void setSubcategories(List<SubCategory> subcategories) { this.subcategories = subcategories; }
	
}
