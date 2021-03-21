package fr.epsi.topaidi.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import fr.epsi.topaidi.dao.CommentDao;
import fr.epsi.topaidi.dao.CommentDaoImplementation;
import fr.epsi.topaidi.entite.Comment;
import fr.epsi.topaidi.entite.Idea;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CommentServiceImplementation implements CommentService {
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction utx;
	
	public Comment getComment(Long commentId) {
		CommentDao commentDao = new CommentDaoImplementation(em, utx);
		return commentDao.getComment(commentId);
	}
	
	public List<Comment> getListComments(Idea idea){
		CommentDao commentDao = new CommentDaoImplementation(em, utx);
		return commentDao.getListComments(idea);
	}
	
	public void addComment(Comment comment) {
		CommentDao commentDao = new CommentDaoImplementation(em, utx);
		commentDao.addComment(comment);
	}
	
	public void deleteComment(Comment comment) {
		CommentDao commentDao = new CommentDaoImplementation(em, utx);
		commentDao.deleteComment(comment);
	}
}
