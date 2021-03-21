package fr.epsi.topaidi.service;

import fr.epsi.topaidi.entite.Idea;
import fr.epsi.topaidi.entite.User;
import fr.epsi.topaidi.entite.Vote;

public interface VoteService {
	Long getTotalVotes(Idea idea);
	
	void like(Vote vote, Idea idea);
	
	void dislike(Vote vote, Idea idea);
	
	void deleteVote(Vote vote, Idea idea);
	
	Boolean checkIfAlreadyVoted(User user, Idea idea);
	
	Vote getUserVote(Idea idea, User user);
	
	Long getLikes(Idea idea);
	
	Long getDislikes(Idea idea);
}
