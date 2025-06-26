package com.example.Gears.Entity;

import java.util.Date;

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
@Table(name = "BILL")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
	@Id
	@Column(name = "bill_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long billID;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(name = "total")
    private long total;
	@Column(name = "payment")
    private String payment;
	@Column(name = "address")
    private String address;
	@Column(name = "date")
    private Date date;
	@Column(name = "name")
    private String name;
	@Column(name = "phone")
    private String phone;
	@Column(name = "status")
    private String status;
	@Transient
	private String products;
	public long getBillID() {
		return billID;
	}
	public void setBillID(long billID) {
		this.billID = billID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	 
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	};
}
