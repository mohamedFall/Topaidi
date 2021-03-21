package fr.epsi.topaidi.service;

import java.util.List;

import fr.epsi.topaidi.entite.Category;

public interface CategoryService {
	
	List<Category> getListCategories();
	
	Category getCategory(Long id);
}
