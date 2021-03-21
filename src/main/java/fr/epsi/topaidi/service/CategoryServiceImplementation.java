package fr.epsi.topaidi.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import fr.epsi.topaidi.dao.CategoryDao;
import fr.epsi.topaidi.dao.CategoryDaoImplementation;
import fr.epsi.topaidi.entite.Category;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CategoryServiceImplementation implements CategoryService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction utx;
	
	public List<Category> getListCategories() {		
		CategoryDao categoryDao = new CategoryDaoImplementation(em, utx);
		return categoryDao.getListCategories();
	}
	
	public Category getCategory(Long id) {
		CategoryDao categoryDao = new CategoryDaoImplementation(em, utx);
		return categoryDao.getCategory(id);
	}
	
}
