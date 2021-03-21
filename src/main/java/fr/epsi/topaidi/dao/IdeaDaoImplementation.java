package fr.epsi.topaidi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import fr.epsi.topaidi.entite.Idea;

public class IdeaDaoImplementation implements IdeaDao {
	private EntityManager em;
	private UserTransaction utx;

	public IdeaDaoImplementation (EntityManager em, UserTransaction utx) {
		this.em = em;
		this.utx = utx;
	}

	public Idea getIdea(Long id) {
		return em.find(Idea.class, id);
	}
	
	public List<Idea> getListIdeas() {
		return em.createQuery("SELECT i FROM Idea i", Idea.class).getResultList();
	}
	
	public void addIdea(Idea idea) {
		try {
			utx.begin();
			em.persist(idea);
			utx.commit();
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public List<Idea> getIdeasUpToDown() {
		String sql = "SELECT i FROM Idea i ORDER BY i.numberOfVotes DESC";
		return em.createQuery(sql, Idea.class).getResultList();
	}

	public List<Idea> getIdeasDownToUp() {
		String sql = "SELECT i FROM Idea i ORDER BY i.numberOfVotes ASC";
		return em.createQuery(sql, Idea.class).getResultList();
	}
}
