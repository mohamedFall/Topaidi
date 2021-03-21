package fr.epsi.topaidi.dao;

import java.util.List;

import fr.epsi.topaidi.entite.Idea;

public interface IdeaDao {
	Idea getIdea(Long id);	
	
	//List<Commentaires> getIdeaComments(Long id);
	
	List<Idea> getListIdeas();
	
	List<Idea> getIdeasUpToDown();
	
	List<Idea> getIdeasDownToUp();
	
	void addIdea(Idea idea);
	
}
