package com.groupdealclone.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupdealclone.app.dao.CategoryDao;
import com.groupdealclone.app.domain.Category;

@Service
public class SimpleCategoryManager implements CategoryManager {

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> getCategories() {
		return categoryDao.getCategories();
	}

	@Override
	public Category getCategory(String name) {
		String searchName = name.trim().toLowerCase();
		return categoryDao.getCategory(searchName);
	}

	@Override
	public Category getCategory(Long id) {
		return categoryDao.getCategory(id);
	}

	@Override
	public void saveCategory(Category category) {
		String saveName = category.getName().trim().toLowerCase();
		category.setName(saveName);
		categoryDao.setCategory(category);
	}
	
	@Override
	public void updateCategory(Category category){
		String saveName = category.getName().trim().toLowerCase();
		category.setName(saveName);
		categoryDao.updateCategory(category);
	}

	@Override
	public List<Category> getCategories(String nameLike) {
		return categoryDao.getCategories("%"+nameLike.trim().toLowerCase()+"%");
	}

}
