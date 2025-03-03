package com.velomagaz.data_integration.entity;

import com.velomagaz.data_integration.entity.enumeration.*;

import java.math.BigDecimal;

import java.sql.Clob;
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

	@Enumerated(EnumType.STRING)
	@Column(name="age_group")
	private AgeGroup ageGroup;
	
	@Enumerated(EnumType.STRING)
	@Column(name="gender_group")
	private Gender gender;
	
	@Column(name="description")
	private Clob description;
	
	@ManyToOne
	@JoinColumn(name="subcategory_id", nullable = false)
	private SubCategory subcategory;
	
	@ManyToOne
	@JoinColumn(name="brand_id", nullable=false)
	private Brand brand;
	
	// -- Navigation --
	@OneToMany(mappedBy="product")
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

	public SubCategory getSubcategory() { return subcategory; }
	public void setSubcategory(SubCategory subcategory) { this.subcategory = subcategory; }
	
	public List<ProductComponent> getProductComponents() { return productComponents; }
	public void setProductComponents(List<ProductComponent> productComponents) { this.productComponents = productComponents; }
	
	public Clob getDescription() { return description; }
	public void setDescription(Clob description) { this.description = description; }
	
	public AgeGroup getAgeGroup() { return ageGroup; }
	public void setAgeGroup(AgeGroup ageGroup) { this.ageGroup = ageGroup; }

	public Gender getGender() { return gender; }
	public void setGender(Gender gender) { this.gender = gender; } 
}
