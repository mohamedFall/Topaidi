package fr.epsi.topaidi.service;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import fr.epsi.topaidi.dao.VoteDao;
import fr.epsi.topaidi.dao.VoteDaoIMplementation;
import fr.epsi.topaidi.entite.Idea;
import fr.epsi.topaidi.entite.User;
import fr.epsi.topaidi.entite.Vote;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class VoteServiceImplementation implements VoteService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction utx;

	public Long getTotalVotes(Idea idea) {
		VoteDao voteDao = new VoteDaoIMplementation(em, utx);
		return voteDao.getTotalVotes(idea);
	}

	public void like(Vote vote, Idea idea) {
		VoteDao voteDao = new VoteDaoIMplementation(em, utx);
		voteDao.like(vote, idea);
	}

	public void dislike(Vote vote, Idea idea) {
		VoteDao voteDao = new VoteDaoIMplementation(em, utx);
		voteDao.dislike(vote, idea);
	}
	
	public void deleteVote(Vote vote, Idea idea) {
		VoteDao voteDao = new VoteDaoIMplementation(em, utx);
		voteDao.deleteVote(vote, idea);
	}

	public Boolean checkIfAlreadyVoted(User user, Idea idea) {
		VoteDao voteDao = new VoteDaoIMplementation(em, utx);
		return voteDao.checkIfAlreadyVoted(user, idea);
	}

	public Vote getUserVote(Idea idea, User user) {
		// TODO Auto-generated method stub
		VoteDao voteDao = new VoteDaoIMplementation(em, utx);
		return voteDao.getUserVote(idea, user);
	}

	public Long getLikes(Idea idea) {
		VoteDao voteDao = new VoteDaoIMplementation(em, utx);
		return voteDao.getLikes(idea);
	}
	
	public Long getDislikes(Idea idea) {
		VoteDao voteDao = new VoteDaoIMplementation(em, utx);
		return voteDao.getDislikes(idea);
	}

}
