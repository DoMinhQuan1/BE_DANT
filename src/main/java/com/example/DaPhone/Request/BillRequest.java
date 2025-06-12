package com.example.DaPhone.Request;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillRequest extends BaseRequest{
	private long billID;
	private Date fromDate;
	private Date toDate;
	private long priceFrom;
	private long priceTo;
	private String userName;
	private long userId;
	public long getBillID() {
		return billID;
	}
	public void setBillID(long billID) {
		this.billID = billID;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public long getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(long priceFrom) {
		this.priceFrom = priceFrom;
	}
	public long getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(long priceTo) {
		this.priceTo = priceTo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
