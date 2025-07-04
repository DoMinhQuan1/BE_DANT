package com.example.Gears.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.Gears.Common.CommonUtils;
import com.example.Gears.Entity.User;
import com.example.Gears.Model.LoginRequest;
import com.example.Gears.Repository.UserRepo;
import com.example.Gears.Request.UserRequest;
import com.example.Gears.Service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public Page<User> findUser(UserRequest userParam, Pageable pageable) {

		Page<User> listPage = userRepo.findAll(new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (userParam.getUserName() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("userName")),
							"%" + userParam.getUserName().trim().toUpperCase() + "%")));
				}
				if (userParam.getEmail() != null) {
					predicates.add(cb.and(cb.like(cb.upper(root.get("userEmail")),
							"%" + userParam.getEmail().trim().toUpperCase() + "%")));
				}
				if (userParam.getPhone() > 0) {
					predicates.add(cb.and(cb.equal(root.get("userPhone"), userParam.getPhone())));
				}

				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		return listPage;
	}
	public User findUserById(Long id) {
		return userRepo.findById(id).get();
	}
	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
	

	@Override
	public Long loginUser(LoginRequest loginRequest) {
		String sql = "";
		Long result = CommonUtils.LOGIN_FAIL;
		Map<String, Object> paramMaps = new HashMap<String, Object>();
		if (loginRequest.getUsername() != null && loginRequest.getPassword() != null) {
			sql = "select user_id from users where user_name= :username and user_pass = :password;";
			paramMaps.put("username", loginRequest.getUsername());
			paramMaps.put("password", loginRequest.getPassword());
		}
		if (!sql.isEmpty()) {
			result = namedParameterJdbcTemplate.queryForObject(sql, paramMaps, Long.class);
			return result;
		}
		return result;

	}

	@Override
	public void updatePassword(String password, Long id) {
		String sql = "";
		Map<String, Object> paramMaps = new HashMap<String, Object>();
		if (password != null && id != null) {
			sql = "update users set user_pass = :password where user_id = :id;";
			paramMaps.put("id", id);
			paramMaps.put("password", password);
		}
		if (!sql.isEmpty()) {
			int status = namedParameterJdbcTemplate.update(sql, paramMaps);
			if (status != 0) {
				System.out.println("Change pass successful");
			} else {
				System.out.println("Change pass failure");
			}
		}
	}

	@Override
	public Long checkUser(String username) {
		String sql = "";
		Map<String, Object> paramMaps = new HashMap<String, Object>();
		if (username != null) {
			sql = "select * from users where user_name= :username ;";
			paramMaps.put("username", username);

		}
		if (!sql.isEmpty()) {
			List<User> result = namedParameterJdbcTemplate.query(sql, paramMaps,
					BeanPropertyRowMapper.newInstance(User.class));
			if (result.size() > 0)
				return CommonUtils.LOGIN_SUCCESS;
			return CommonUtils.LOGIN_FAIL;
		}
		return CommonUtils.LOGIN_FAIL;
	}

}
