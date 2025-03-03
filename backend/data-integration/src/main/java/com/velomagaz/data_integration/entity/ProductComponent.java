package com.velomagaz.data_integration.entity;

import jakarta.persistence.*;

@Entity
@Table(name="product_component", 
uniqueConstraints = @UniqueConstraint(columnNames= {"product_id","component_id"}))
public class ProductComponent {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="desc_value", length=100)
	private String descValue;
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="component_id", nullable=false)
	private Component component;

	// -- Setters and Getters --
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getDescValue() { return descValue; }
	public void setDescValue(String descValue) { this.descValue = descValue; }
	
	public Product getProduct() { return product; }
	public void setProductId(Product product) { this.product = product; }
	
	public Component getComponent() { return component; }
	public void setComponent(Component component) { this.component = component; }
}
