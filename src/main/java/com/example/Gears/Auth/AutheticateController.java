package com.example.Gears.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Gears.Common.CommonUtils;
import com.example.Gears.Model.LoginRequest;
import com.example.Gears.Model.LoginResponse;
import com.example.Gears.Service.UserAdminService;
import com.example.Gears.Service.UserService;

@RestController
@RequestMapping(path = "/api/authenticate")
public class AutheticateController {
	@Autowired
	private UserAdminService userAdminService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommonUtils commonUtils;
	@PostMapping(value = "/admin/login")
	public ResponseEntity<LoginResponse> loginAdmin(@RequestBody LoginRequest loginRequest) {
		if (userAdminService.loginAdmin(loginRequest) == CommonUtils.LOGIN_SUCCESS) {
			String auth;
			try {
				auth = commonUtils.createToken(loginRequest.getUsername(), loginRequest.getPassword(), "1");
			} catch (Exception e) {
				return new ResponseEntity<LoginResponse>(new LoginResponse("false", ""), HttpStatus.OK);
			}
			return new ResponseEntity<LoginResponse>(new LoginResponse(auth, loginRequest.getUsername()),
					HttpStatus.OK);
		}
		return new ResponseEntity<LoginResponse>(new LoginResponse("false", ""), HttpStatus.OK);

	}
	@PostMapping(value = "/user/login")
	public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
		if (userService.loginUser(loginRequest) != CommonUtils.LOGIN_FAIL) {
			String auth;
			try {
				auth = commonUtils.createToken(loginRequest.getUsername(), loginRequest.getPassword(), "0");
			} catch (Exception e) {
				return new ResponseEntity<LoginResponse>(new LoginResponse("false", ""), HttpStatus.OK);
			}
			return new ResponseEntity<LoginResponse>(
					new LoginResponse(auth, loginRequest.getUsername(), userService.loginUser(loginRequest)),
					HttpStatus.OK);
		}
		return new ResponseEntity<LoginResponse>(new LoginResponse("false", ""), HttpStatus.OK);

	}
	public UserAdminService getUserAdminService() {
		return userAdminService;
	}
	public void setUserAdminService(UserAdminService userAdminService) {
		this.userAdminService = userAdminService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public CommonUtils getCommonUtils() {
		return commonUtils;
	}
	public void setCommonUtils(CommonUtils commonUtils) {
		this.commonUtils = commonUtils;
	}
}
