package com.groupdealclone.app.service;

import java.util.List;

import com.groupdealclone.app.domain.Category;

public interface CategoryManager {
	
	public List<Category> getCategories();
	
	public Category getCategory(String name);

	public Category getCategory(Long id);
	
	public void updateCategory(Category category);
	
	public void saveCategory(Category category);

	public List<Category> getCategories(String nameLike);
	
	public List<Category> getCategories(String ... nameIn);
	
}
