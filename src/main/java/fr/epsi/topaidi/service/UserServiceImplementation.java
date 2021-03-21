package fr.epsi.topaidi.service;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import fr.epsi.topaidi.dao.UserDao;
import fr.epsi.topaidi.dao.UserDaoImplementation;
import fr.epsi.topaidi.entite.User;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserServiceImplementation implements UserService {
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction utx;

	public void addUser(User user) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImplementation(em, utx);
		userDao.addUser(user);
	}

	public boolean checkUser(String email, String pass) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImplementation(em, utx);
		return userDao.checkUser(email, pass);
	}

	public boolean checkEmailUser(String email) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImplementation(em, utx);
		return userDao.checkEmailUser(email);
	}

	public User getUser(Long id) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImplementation(em, utx);
		return userDao.getUser(id);
	}
	
	public User getUser(String email) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImplementation(em, utx);
		return userDao.getUser(email);
	}
}
