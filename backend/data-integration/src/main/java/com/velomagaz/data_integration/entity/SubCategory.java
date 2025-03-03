package com.velomagaz.data_integration.entity;

import java.sql.Clob;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="subcategory")
public class SubCategory {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="subcategory_name", unique=true)
	private String subcategoryName;
	
	@Column(name="description")
	private Clob description;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable=false)
	private Category category;
	
	// -- Navigation --
	@OneToMany(mappedBy="subcategory")
	private List<Product> products;
	
	// -- Setters and Getters --
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getSubcategoryName() { return subcategoryName;}
	public void setSubcategoryName(String subcategoryName ) { this.subcategoryName = subcategoryName; }

	public Clob getDescription() { return description; }
	public void setDescription(Clob description) { this.description = description; }
	
	public Category getCategory() { return category; }
	public void setCategory(Category categoryId) { this.category = categoryId; }
	
}
