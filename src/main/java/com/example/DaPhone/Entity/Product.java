package com.example.Gears.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Setter
@Getter 
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@Column(name = "product_id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productID;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	@Column(name = "product_name")
	private String productName;
	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = false)
	private Brand brand;
	@Column(name = "product_image")
	private String productImage;
	@Column(name = "product_price")
	private long productPrice;
	@Column(name = "product_description")
	private String productDescription;
	@Column(name = "product_importprice")
	private long productImportPrice;
	@Column(name = "product_quantily")
	private int productQuantily;
	@Column(name = "product_ram")
	private String productRAM;
	@Column(name = "product_dimensions")
	private String productDimensions;
	@Column(name = "product_weight")
	private byte productWeight;
	@Column(name = "product_material")
	private String productMaterial;
	@Column(name = "product_quantity_sold")
	private int productQuantitySold;
	@Column(name = "product_marketprice")
	private long productMarketprice;
	@Transient
	private int quanlityBuy;
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public long getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public long getProductImportPrice() {
		return productImportPrice;
	}
	public void setProductImportPrice(long productImportPrice) {
		this.productImportPrice = productImportPrice;
	}
	public int getProductQuantily() {
		return productQuantily;
	}
	public void setProductQuantily(int productQuantily) {
		this.productQuantily = productQuantily;
	}
	public String getProductRAM() {
		return productRAM;
	}
	public void setProductRAM(String productRAM) {
		this.productRAM = productRAM;
	}
	public String getProductDimensions() {
		return productDimensions;
	}
	public void setProductDimensions(String productDimensions) {
		this.productDimensions = productDimensions;
	}
	public byte getProductWeight() {
		return productWeight;
	}
	public void setProductWeight(byte productWeight) {
		this.productWeight = productWeight;
	}
	public String getProductMaterial() {
		return productMaterial;
	}
	public void setProductMaterial(String productMaterial) {
		this.productMaterial = productMaterial;
	}
	public int getProductQuantitySold() {
		return productQuantitySold;
	}
	public void setProductQuantitySold(int productQuantitySold) {
		this.productQuantitySold = productQuantitySold;
	}
	public long getProductMarketprice() {
		return productMarketprice;
	}
	public void setProductMarketprice(long productMarketprice) {
		this.productMarketprice = productMarketprice;
	}
	public int getQuanlityBuy() {
		return quanlityBuy;
	}
	public void setQuanlityBuy(int quanlityBuy) {
		this.quanlityBuy = quanlityBuy;
	}
	

}
