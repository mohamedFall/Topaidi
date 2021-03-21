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

import fr.epsi.topaidi.entite.User;

public class UserDaoImplementation implements UserDao {
	private EntityManager em;
	private UserTransaction utx;
	
	public UserDaoImplementation(EntityManager em, UserTransaction utx) {
		this.em = em;
		this.utx = utx;
	}

	public void addUser(User user) {
		
		try {
			utx.begin();
			em.persist(user);
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
	
	public boolean checkUser(String email, String pwd) {
		// TODO Auto-generated method stub
		String sql   = "select u from User u where u.email = :email And u.password = :pwd ";		
		Query  query = em.createQuery(sql, User.class);
		query.setParameter("email", email);
		query.setParameter("pwd", pwd);
		
		List<User> user = (List<User>) query.getResultList();
		return user.isEmpty();
	}

	public boolean checkEmailUser(String email) {
		// TODO Auto-generated method stub
		Query  query = em.createQuery("select u from User u where u.email = :email", User.class).setParameter("email", email);
		List<User> user = (List<User>) query.getResultList();		
		return user.isEmpty();
	}
	
	public User getUser(Long id) {
		return em.find(User.class, id);
	}
	
	public User getUser(String email) {
		return em.createQuery("select u from User u where u.email = :email", User.class).setParameter("email", email).getSingleResult();
	}
}
