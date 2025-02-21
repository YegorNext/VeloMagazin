package com.velomagaz.app.Entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "product")
public class Product {
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "product_name", length = 100)
	private String productName;
	
	@Column(name = "price", precision = 10, scale = 2)
	private BigDecimal price;
	
	@Column(name = "image", columnDefinition = "LONGBLOB")
	private byte[] image;
	
	@Column(name="model", length=30)
	private String model;

	@ManyToOne
	@JoinColumn(name="category_id", nullable = false)
	private Category categoryId;
	
	// -- Navigation --
	@OneToMany(mappedBy="productId")
	private List<ProductComponent> productComponents; 
	
	// -- Setters and Getters --
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	
	public String getProductName() { return productName; }
	public void setProductName(String productName) { this.productName = productName; }
	
	public BigDecimal getPrice() { return price; }
	public void setPrice(BigDecimal price) { this.price = price; }
	
	public byte[] getImage() { return image; }
	public void setImage(byte[] image) { this.image = image; }
	
	public String getModel() { return model; }
	public void setModel(String model) { this.model = model; }
	
	public Category getCategoryId() { return categoryId; }
	public void setCategoryId(Category categoryId) { this.categoryId = categoryId; }
	
	public List<ProductComponent> GetProductComponents() { return productComponents; }
	public void setProductComponents(List<ProductComponent> productComponents) { this.productComponents = productComponents; }
	

}
