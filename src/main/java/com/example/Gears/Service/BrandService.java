package com.example.Gears.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Gears.Entity.Brand;
import com.example.Gears.Entity.Product;
import com.example.Gears.Request.BrandRequest;

@Service
public interface BrandService {
	public Page<Brand> findBrand(BrandRequest brandParam, Pageable pageable);
	public List<Brand> findAll();
	public Brand saveBrand(Brand brand);
	public void deleteBrand(Long id);
}
