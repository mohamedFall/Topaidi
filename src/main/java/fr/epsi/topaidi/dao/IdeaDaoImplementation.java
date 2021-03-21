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

	/*
	 * public List<Commentaires> getIdeaComments(Long id) { String sql =
	 * "select c from Commentaires c join users u on c.user_id join idea i on c.idea_id = :id"
	 * ; return em.createQuery(sql, Commentaires.class).setParameter("id",
	 * id).getResultList(); }
	 */
	
	public List<Idea> getListIdeas() {
		return em.createQuery("SELECT i FROM Idea i", Idea.class).getResultList();
	}

	public List<Idea> getIdeasRanking() {
		String sql = "SELECT i FROM Idea i group by i.id order by i.top DESC, SUM(i.likes + i.dislikes) DESC, i.createdAt DESC";
		return em.createQuery(sql, Idea.class).getResultList();
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
}
