package com.velomagaz.app.entity;

import java.sql.Clob;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="component")
public class Component {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="component_name", length=100, nullable = false, unique=true)
	private String componentName;
	
	@Column(name="description")
	private Clob description;
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	private Category categoryId;
	
	// -- Navigation --
	@OneToMany(mappedBy="componentId")
	private List<ProductComponent> productComponents; 
	
	// -- Setters and Getters --
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getComponentName() { return componentName; }
	public void setComponentName(String componentName) { this.componentName = componentName; }
	
	public Clob getDescription() { return description; }
	public void setDescription(Clob description) { this.description = description; }
	
	public Category getCategoryId() { return categoryId; }
	public void setCategoryId(Category categoryId) { this.categoryId = categoryId; }
	
	public List<ProductComponent> getProductComponents() { return productComponents; }
	public void setProductComponents(List<ProductComponent> productComponents) { this.productComponents = productComponents; }
}
