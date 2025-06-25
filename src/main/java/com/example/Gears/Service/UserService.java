package com.example.Gears.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Gears.Entity.User;
import com.example.Gears.Model.LoginRequest;
import com.example.Gears.Request.UserRequest;

@Service
public interface UserService {
	public Page<User> findUser(UserRequest userParam, Pageable pageable);
	public User saveUser(User user);
	public User findUserById(Long id);
	public void deleteUser(Long id);
	public Long loginUser(LoginRequest loginRequest);
	public void updatePassword(String password, Long id);
	public Long checkUser(String username);
}
