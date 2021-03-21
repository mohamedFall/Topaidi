package fr.epsi.topaidi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import fr.epsi.topaidi.entite.Comment;
import fr.epsi.topaidi.entite.Idea;

public class CommentDaoImplementation implements CommentDao {
	private EntityManager em;
	private UserTransaction utx;

	public CommentDaoImplementation(EntityManager em, UserTransaction utx) {
		this.em = em;
		this.utx = utx;
	}
	
	public Comment getComment(Long commentId) {
		return em.find(Comment.class, commentId);
	}

	public List<Comment> getListComments(Idea idea) {
		return em.createQuery("SELECT c FROM Comment c JOIN c.user  WHERE c.idea = :idea ORDER BY c.createdAt ASC", Comment.class).setParameter("idea", idea).getResultList();
	}

	public void addComment(Comment comment) {
		try {
			utx.begin();
			em.persist(comment);
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

	public void deleteComment(Comment comment) {
		try {
		    utx.begin();
		    Query  query = em.createQuery("DELETE FROM Comment c WHERE c.id = :id");
			query.setParameter("id", comment.getId());
			query.executeUpdate();
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
