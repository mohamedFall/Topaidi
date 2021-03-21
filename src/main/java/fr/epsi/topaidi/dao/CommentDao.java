package fr.epsi.topaidi.dao;

import java.util.List;

import fr.epsi.topaidi.entite.Comment;
import fr.epsi.topaidi.entite.Idea;

public interface CommentDao {
	Comment getComment(Long commentId);
	
	List<Comment> getListComments(Idea idea);
	
	void addComment(Comment comment);
	
	void deleteComment(Comment comment);
}
