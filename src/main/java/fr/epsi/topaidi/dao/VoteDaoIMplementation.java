package fr.epsi.topaidi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import fr.epsi.topaidi.entite.Idea;
import fr.epsi.topaidi.entite.User;
import fr.epsi.topaidi.entite.Vote;

public class VoteDaoIMplementation implements VoteDao {
	private EntityManager em;
	private UserTransaction utx;
	
	public VoteDaoIMplementation(EntityManager entityManager, UserTransaction userTransaction) {
		this.em = entityManager;
		this.utx = userTransaction;
	}
	
	public Long getTotalVotes(Idea idea) {
		// TODO Auto-generated method stub
		return (long) em.createQuery("SELECT v FROM Vote v WHERE v.idea = :idea", Vote.class).setParameter("idea", idea).getMaxResults();
	}

	public void like(Vote vote, Idea idea) {
		try {
			utx.begin();
			em.persist(vote);
			Idea _idea = em.find(Idea.class, idea.getId());
			_idea.setNumberOfVotes(_idea.getNumberOfVotes() + 1);
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

	public void dislike(Vote vote, Idea idea) {
		try {
			utx.begin();
			em.persist(vote);
			Idea _idea = em.find(Idea.class, idea.getId());
			_idea.setNumberOfVotes(_idea.getNumberOfVotes() + 1);
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
	
	public void deleteVote(Vote vote, Idea idea) {
		try {
		    utx.begin();
		    Query  query = em.createQuery("DELETE FROM Vote v WHERE v.id = :id");
			query.setParameter("id", vote.getId());
			query.executeUpdate();
			Idea _idea = em.find(Idea.class, idea.getId());
			_idea.setNumberOfVotes(_idea.getNumberOfVotes() - 1);
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

	public Boolean checkIfAlreadyVoted(User user, Idea idea) {
		Query  query = em.createQuery("SELECT v FROM Vote v WHERE v.user = :user AND v.idea = :idea", Vote.class);
		query.setParameter("user", user);
		query.setParameter("idea", idea);		
		List<Vote> vote = (List<Vote>) query.getResultList();
		return !vote.isEmpty();
	}

	public Vote getUserVote(Idea idea, User user) {
		try {
			return (Vote) em.createQuery("SELECT v FROM Vote v WHERE v.idea = :idea AND v.user = :user", Vote.class)
					.setParameter("idea", idea)
					.setParameter("user", user)
					.getSingleResult();
		} catch (NoResultException e){
    		return null;
	    }
		
	}

	public Long getLikes(Idea idea) {
		return (Long) em.createQuery("SELECT COUNT(v) FROM Vote v WHERE v.idea = :idea AND v.vote = :vote")
				.setParameter("vote", 1)
				.setParameter("idea", idea)
				.getSingleResult();
	}
	
	public Long getDislikes(Idea idea) {
		return (Long) em.createQuery("SELECT COUNT(v) FROM Vote v WHERE v.idea = :idea AND v.vote = :vote")
				.setParameter("vote", 0)
				.setParameter("idea", idea)
				.getSingleResult();
	}

}
