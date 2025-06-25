package com.example.Gears.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Gears.Entity.Config;

@Repository
public interface ConfigRepo extends JpaRepository<Config, Long>{
	public Config getByName(String name);
}
