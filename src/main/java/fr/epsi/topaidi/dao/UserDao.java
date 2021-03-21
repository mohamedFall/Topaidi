package fr.epsi.topaidi.dao;

import fr.epsi.topaidi.entite.User;

public interface UserDao {
	void addUser(User user);

	boolean checkUser(String email, String pass);

	boolean checkEmailUser(String email);

	User getUser(Long id);
	
	User getUser(String email);
}
