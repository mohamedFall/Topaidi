package fr.epsi.topaidi.dao;

import java.util.List;

import fr.epsi.topaidi.entite.Idea;

public interface IdeaDao {
	Idea getIdea(Long id);	
	
	//List<Commentaires> getIdeaComments(Long id);
	
	List<Idea> getListIdeas();
	
	List<Idea> getIdeasRanking();
	
	void addIdea(Idea idea);
	
}
