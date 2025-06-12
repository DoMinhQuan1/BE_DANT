package com.example.DaPhone.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	private String auth;
	private String username;
	private Long userId;
	public LoginResponse(String auth, String username) {
		super();
		this.auth = auth;
		this.username = username;
	}
	
	public LoginResponse(String auth, String username, Long userId) {
		super();
		this.auth = auth;
		this.username = username;
		this.userId = userId;
	}

	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
