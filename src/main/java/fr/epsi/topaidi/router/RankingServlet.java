package fr.epsi.topaidi.router;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.epsi.topaidi.service.CategoryService;
import fr.epsi.topaidi.service.IdeaService;
import fr.epsi.topaidi.service.UserService;

@WebServlet("/ranking")
public class RankingServlet extends HttpServlet{
	@EJB
	private IdeaService ideaService;
	@EJB
	private UserService userService;
	@EJB
	private CategoryService categoryService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
		HttpSession session = req.getSession();
		
		req.setAttribute("isConnected", session.getAttribute("isConnected"));
		
		if(req.getParameterMap().containsKey("action")) {
			if(req.getParameter("action").equals("upToDown")) {
				req.setAttribute("listIdeasRanking", ideaService.getIdeasRanking());
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/ranking.jsp").forward(req, resp);
			} else if(req.getParameter("action").equals("downToUp")) {
				req.setAttribute("listIdeasRanking", ideaService.getIdeasRanking());
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/ranking.jsp").forward(req, resp);
			} else if(null == session.getAttribute("isConnected")) {
		        resp.sendRedirect("signIn?action=signOn");
			} else if(req.getParameter("action").equals("exit")) {
				session.removeAttribute("isConnected");
			    resp.sendRedirect("signIn?action=signOn");
			}
		} else if(session.getAttribute("isConnected") == null) {
	        resp.sendRedirect("signIn?action=signOn");
		} else {
			req.setAttribute("listIdeas", ideaService.getListIdeas());
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/ranking.jsp").forward(req, resp);
		}
    }
}
