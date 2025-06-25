package com.example.Gears.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bill_detail")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillDetail {
	@Id
	@Column(name = "bill_detail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long billDetailID;
	
	@ManyToOne
	@JoinColumn(name = "bill_id", nullable = false)
	private Bill bill;
	
	public long getBillDetailID() {
		return billDetailID;
	}
	public void setBillDetailID(long billDetailID) {
		this.billDetailID = billDetailID;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@Column(name = "price")
    private long price;
	@Column(name = "quantity")
    private int quantity;
}
