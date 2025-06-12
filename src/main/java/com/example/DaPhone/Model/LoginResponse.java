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
	
	// Constructor với 2 tham số
	// a giải thích qua thì cái @AllArgsConstructor nó tự động thay cho việc e khai báo mục get set kia rồi.
	// nên là e dùng cái @AllArgsConstructor mà còn khai báo get set thì nó sẽ bị lỗi báo là đã có constructor rồi.
	public LoginResponse(String auth, String username) {
		super();
		this.auth = auth;
		this.username = username;
	}
}
