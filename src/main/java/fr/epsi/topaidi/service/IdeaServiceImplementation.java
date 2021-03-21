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
import fr.epsi.topaidi.dao.IdeaDao;
import fr.epsi.topaidi.dao.IdeaDaoImplementation;
import fr.epsi.topaidi.entite.Category;
import fr.epsi.topaidi.entite.Idea;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class IdeaServiceImplementation implements IdeaService {
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction utx;

	public Idea getIdea(Long id) {
		IdeaDao ideaDao = new IdeaDaoImplementation(em, utx);
		return  ideaDao.getIdea(id);
	}
	
	public List<Idea> getListIdeas() {
		IdeaDao ideaDao = new IdeaDaoImplementation(em, utx);
		return  ideaDao.getListIdeas();
		
	}

	public List<Idea> getIdeasRanking() {
		IdeaDao ideaDao = new IdeaDaoImplementation(em, utx);
		return  ideaDao.getIdeasRanking();
	}
	
	public void addIdea(Idea idea) {
		IdeaDao ideaDao = new IdeaDaoImplementation(em, utx);
		ideaDao.addIdea(idea);
	}
}
