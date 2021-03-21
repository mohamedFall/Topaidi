package fr.epsi.topaidi.dao;

import java.util.List;

import fr.epsi.topaidi.entite.Category;

public interface CategoryDao {

	List<Category> getListCategories();
	
	Category getCategory(Long id);
}
