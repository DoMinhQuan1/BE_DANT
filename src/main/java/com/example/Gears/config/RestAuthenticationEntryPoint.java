package com.example.Gears.config;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	 public void commence(HttpServletRequest request, HttpServletResponse response,
		      AuthenticationException authException) throws IOException {
//		    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		    response.getWriter().write("Unauthorized");
		  }

}
