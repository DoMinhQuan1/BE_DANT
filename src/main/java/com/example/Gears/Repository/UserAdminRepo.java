package com.example.Gears.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Gears.Entity.Admin;

@Repository
public interface UserAdminRepo extends JpaRepository<Admin, Long> {

}
