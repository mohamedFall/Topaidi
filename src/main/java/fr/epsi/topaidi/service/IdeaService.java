package fr.epsi.topaidi.service;

import java.util.List;

import fr.epsi.topaidi.entite.Idea;

public interface IdeaService {
	Idea getIdea(Long id);
	
	List<Idea> getListIdeas();
	
	List<Idea> getIdeasRanking();
	
	void addIdea(Idea idea);

}
