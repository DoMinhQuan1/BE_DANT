package com.example.Gears.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Gears.Entity.Bill;
import com.example.Gears.Entity.User;

@Repository
public interface UserRepo  extends JpaRepository<User, Long>{

	Page<User> findAll(Specification<User> specification, Pageable pageable);

}
