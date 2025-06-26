package com.example.Gears.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Gears.Entity.Category;
import com.example.Gears.Request.CategoryRequest;
@Service
public interface CategoryService {
	public Page<Category> findCategory(CategoryRequest categoryParam, Pageable pageable);
	public List<Category> findAll();
	public Category saveCategory(Category category);
	public void deleteCategory(Long id);
}
