package com.example.Gears.Service;

import org.springframework.stereotype.Service;

import com.example.Gears.Model.LoginRequest;

@Service
public interface UserAdminService {
	public Long loginAdmin(LoginRequest loginRequest);
}
