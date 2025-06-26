package com.example.Gears.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "contact")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
	@Id
	@Column(name = "contact_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long contactID;
	@Column(name = "contact_name")
    private String contactName;
	public long getContactID() {
		return contactID;
	}
	public void setContactID(long contactID) {
		this.contactID = contactID;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactWeb() {
		return contactWeb;
	}
	public void setContactWeb(String contactWeb) {
		this.contactWeb = contactWeb;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactTitle() {
		return contactTitle;
	}
	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}
	public String getContactMessage() {
		return contactMessage;
	}
	public void setContactMessage(String contactMessage) {
		this.contactMessage = contactMessage;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Column(name = "contact_web")
    private String contactWeb;
	@Column(name = "contact_email")
    private String contactEmail;
	@Column(name = "contact_title")
    private String contactTitle;
	@Column(name = "contact_message")
    private String contactMessage;
	@Column(name = "contact_date")
    private Date date;
}
