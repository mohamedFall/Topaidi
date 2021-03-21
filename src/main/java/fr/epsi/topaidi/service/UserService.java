package fr.epsi.topaidi.service;

import fr.epsi.topaidi.entite.User;

public interface UserService {
	void addUser(User user);
	
	boolean checkUser(String email, String pass);

	boolean checkEmailUser(String email);

	User getUser(Long userId);
	
	User getUser(String email);
}
