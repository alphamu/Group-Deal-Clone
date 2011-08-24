package com.groupdealclone.app.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.groupdealclone.app.domain.Category;
import com.groupdealclone.app.service.CategoryManager;

@Controller
public class CategoryController {
	//private static final Logger logger = LoggerFactory.getLogger(NewDealController.class);
	
	@Autowired
	private CategoryManager categoryManager;


	@RequestMapping(value = "category/{categoryId}/edit", method = RequestMethod.GET)
	public String showEditForm(@PathVariable("categoryId") long categoryId, Map<String, Object> model) {
		Category categoryForm = this.categoryManager.getCategory(new Long(categoryId));
		model.put("category", categoryForm);
		return "category/edit";
	}

	@RequestMapping(value = "category/{categoryId}/edit", method = RequestMethod.POST)
	public String processEditForm(@Valid Category categoryForm, BindingResult result, Map<String,Object> model) {
		if (result.hasErrors()) {
			return "category/edit";
		}
		model.put("category", categoryForm);
		//model.put("categorys", this.categoryManager.getCategories());
		this.categoryManager.updateCategory(categoryForm);
		return "category/added";
	}
	
	@RequestMapping(value = "category/new", method = RequestMethod.GET)
	public String showNewForm(Map<String, Object> model) {
		Category categoryForm = new Category();
		model.put("category", categoryForm);
		return "category/new";
	}

	@RequestMapping(value = "category/new", method = RequestMethod.POST)
	public String processNewForm(@Valid Category categoryForm, BindingResult result, Map<String,Object> model) {
		if (result.hasErrors()) {
			return "category/new";
		}
		this.categoryManager.saveCategory(categoryForm);
		model.put("category", categoryForm);
		//model.put("categorys", this.categoryManager.getCategories());
		return "category/added";
	}
	
	public void setCategoryManager(CategoryManager categoryManager){
		this.categoryManager = categoryManager;
	}
}
