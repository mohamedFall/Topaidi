package fr.epsi.topaidi.router;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.epsi.topaidi.entite.Category;
import fr.epsi.topaidi.entite.Comment;
import fr.epsi.topaidi.entite.Idea;
import fr.epsi.topaidi.entite.Vote;
import fr.epsi.topaidi.service.CategoryService;
import fr.epsi.topaidi.service.CommentService;
import fr.epsi.topaidi.service.IdeaService;
import fr.epsi.topaidi.service.UserService;
import fr.epsi.topaidi.service.VoteService;

@WebServlet("/idea")
public class IdeaServlet extends HttpServlet {

	@EJB
	private IdeaService ideaService;
	@EJB
	private UserService userService;
	@EJB
	private CategoryService categoryService;
	@EJB
	private CommentService commentService;
	@EJB
	private VoteService voteService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
		HttpSession session = req.getSession();
		
		req.setAttribute("isConnected", session.getAttribute("isConnected"));

		if(null == session.getAttribute("isConnected")) {
	        resp.sendRedirect("signIn?action=signOn");
		} else  {
			Long userId = (Long) session.getAttribute("userId");
			if(req.getParameter("action").equals("create")) {
				req.setAttribute("listCategories", categoryService.getListCategories());
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/createIdea.jsp").forward(req, resp);
			} else if(req.getParameter("action").equals("view")) {
				String ideaid = req.getParameter("id");
				System.out.println("here the idea id is" + Long.parseLong(ideaid));
				
				Long ideaId = Long.parseLong(req.getParameter("id"));
				req.setAttribute("idea", ideaService.getIdea(ideaId));
				req.setAttribute("likes", voteService.getLikes(ideaService.getIdea(ideaId)));
				req.setAttribute("dislikes", voteService.getDislikes(ideaService.getIdea(ideaId)));
				req.setAttribute("listComments", commentService.getListComments(ideaService.getIdea(ideaId)));
				req.setAttribute("alreadyVoted", voteService.checkIfAlreadyVoted(userService.getUser(userId), ideaService.getIdea(ideaId)));
				req.setAttribute("sessionUserId", session.getAttribute("userId"));
				
				Vote userVote = voteService.getUserVote(ideaService.getIdea(ideaId), userService.getUser(userId));
				if(userVote != null)
					req.setAttribute("userVote", voteService.getUserVote(ideaService.getIdea(ideaId), userService.getUser(userId)).getVote());
				
				System.out.println("number of likes is" + voteService.getLikes(ideaService.getIdea(ideaId)));
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/viewIdea.jsp").forward(req, resp);
			} else if(req.getParameter("action").equals("exit")) {
				session.removeAttribute("isConnected");
			    resp.sendRedirect("signIn?action=signOn");
			}
		}
    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
		HttpSession session = req.getSession();
		Long userId = (Long) session.getAttribute("userId");
		
		if(req.getParameter("actionPost").equals("createIdea")) {
			Idea idea = new Idea();
			idea.setTitle(req.getParameter("title"));
			idea.setDescription(req.getParameter("description"));
			idea.setImage(req.getParameter("image"));
			idea.setUser(userService.getUser(userId));
			idea.setCategory(categoryService.getCategory(Long.parseLong(req.getParameter("category"))));
			
			ideaService.addIdea(idea);
			
			resp.sendRedirect("home");
		} else if(req.getParameter("actionPost").equals("addComment")) {
			Comment comment = new Comment();
			comment.setContent(req.getParameter("content"));
			comment.setIdea(ideaService.getIdea(Long.parseLong(req.getParameter("idea"))));
			comment.setUser(userService.getUser(userId));
			
			commentService.addComment(comment);
			
			resp.sendRedirect("idea?action=view&id=" + Long.parseLong(req.getParameter("idea")));
		} else if(req.getParameter("actionPost").equals("deleteComment")) {
			Long commentId = Long.parseLong(req.getParameter("commentId"));
			
			commentService.deleteComment(commentService.getComment(commentId));
			
			resp.sendRedirect("idea?action=view&id=" + Long.parseLong(req.getParameter("idea")));
		} else if(req.getParameter("actionPost").equals("likeIdea")) {
			Vote vote = new Vote();
			vote.setIdea(ideaService.getIdea(Long.parseLong(req.getParameter("idea"))));
			vote.setUser(userService.getUser(userId)); 
			vote.setVote(1);
			
			voteService.like(vote, ideaService.getIdea(Long.parseLong(req.getParameter("idea"))));
			
			resp.sendRedirect("idea?action=view&id=" + Long.parseLong(req.getParameter("idea")));
		} else if(req.getParameter("actionPost").equals("dislikeIdea")) {
			Vote vote = new Vote();
			vote.setIdea(ideaService.getIdea(Long.parseLong(req.getParameter("idea"))));
			vote.setUser(userService.getUser(userId)); 
			vote.setVote(0);
			
			voteService.dislike(vote, ideaService.getIdea(Long.parseLong(req.getParameter("idea"))));
			
			resp.sendRedirect("idea?action=view&id=" + Long.parseLong(req.getParameter("idea")));
		} else if(req.getParameter("actionPost").equals("deleteVote")) {	
			Long ideaId = Long.parseLong(req.getParameter("id"));
			Vote vote = voteService.getUserVote(ideaService.getIdea(ideaId), userService.getUser(userId));
			voteService.deleteVote(vote, ideaService.getIdea(Long.parseLong(req.getParameter("idea"))));
			
			System.out.println("deleted vote  is " + voteService.getUserVote(ideaService.getIdea(ideaId), userService.getUser(userId)));
			
			resp.sendRedirect("idea?action=view&id=" + Long.parseLong(req.getParameter("idea")));
		}
    }
	
}
