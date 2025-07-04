package com.example.Gears.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.Gears.Common.CommonUtils;
import com.example.Gears.Entity.Admin;
import com.example.Gears.Model.LoginRequest;
import com.example.Gears.Service.UserAdminService;
@Service
public class UserAdminServiceImpl implements UserAdminService{
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public Long loginAdmin(LoginRequest loginRequest) {
		String sql = "";
		Map<String, Object> paramMaps = new HashMap<String, Object>();
		if (loginRequest.getUsername() != null && loginRequest.getPassword() != null) {
			sql = "select * from admin where user_name= :username and user_pass = md5(:password);";
			paramMaps.put("username", loginRequest.getUsername());
			paramMaps.put("password", loginRequest.getPassword());
			System.out.print(sql);
		}
		if (!sql.isEmpty()) {
			List<Admin> result = namedParameterJdbcTemplate.query(sql, paramMaps, BeanPropertyRowMapper.newInstance(Admin.class));
			if (result.size()>0)
				return CommonUtils.LOGIN_SUCCESS;
			return CommonUtils.LOGIN_FAIL;
		}
		return CommonUtils.LOGIN_FAIL;

	}
}
