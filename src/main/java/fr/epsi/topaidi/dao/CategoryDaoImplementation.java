package fr.epsi.topaidi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import fr.epsi.topaidi.entite.Category;

public class CategoryDaoImplementation implements CategoryDao {

	private EntityManager em;
	private UserTransaction utx;

	public CategoryDaoImplementation (EntityManager em, UserTransaction utx) {
		this.em = em;
		this.utx = utx;
	}

	public Category getCategory(Long id) {
		return em.find(Category.class, id);
	}
	
	public List<Category> getListCategories() {
		return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
	}
	
}
