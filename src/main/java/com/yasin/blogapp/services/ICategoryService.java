package com.yasin.blogapp.services;

import java.util.List;

import com.yasin.blogapp.entity.Category;

public interface ICategoryService {
	List<Category> getAllCategories();

	Category getCategoryById(Long id);

	Category createCategory(Category category);

	Category updateCategory(Category category);

	void deleteCategoryById(Long id);

}
