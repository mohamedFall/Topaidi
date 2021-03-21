package fr.epsi.topaidi.service;

import java.util.List;

import fr.epsi.topaidi.entite.Comment;
import fr.epsi.topaidi.entite.Idea;

public interface CommentService {
	Comment getComment(Long id);
	
	List<Comment> getListComments(Idea idea);
	
	void addComment(Comment comment);
	
	void deleteComment(Comment comment);
}
