package com.yasin.blogapp.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yasin.blogapp.entity.Category;
import com.yasin.blogapp.exceptions.ResourceNotFoundException;
import com.yasin.blogapp.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories() {

		return sortCategoriesByName(this.categoryRepository.findAll());
	}

	List<Category> sortCategoriesByName(List<Category> categories) {
		// sorting categories by category name using stream api
		return categories.stream().sorted(Comparator.comparing(Category::getName)).collect(Collectors.toList());
	}

	@Override
	public Category getCategoryById(Long id) {
		return this.categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id"));
	}

	@Override
	public Category createCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return this.categoryRepository.save(category);

	}

	@Override
	public void deleteCategoryById(Long id) {
		Category category = this.getCategoryById(id);
		this.categoryRepository.delete(category);
	}

}
