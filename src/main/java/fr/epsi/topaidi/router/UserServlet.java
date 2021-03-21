package fr.epsi.topaidi.router;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.epsi.topaidi.entite.User;
import fr.epsi.topaidi.service.UserService;

@WebServlet("/signIn")
public class UserServlet extends HttpServlet {
	
	@EJB
	private UserService userService;
	
	/* Message d'erreur affiché */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession session = req.getSession();

		if(req.getParameter("action").equals("signUp")) {
			 this.getServletContext().getRequestDispatcher("/WEB-INF/pages/signUp.jsp").forward(req, resp);
		} else if(req.getParameter("action").equals("signOn")) {
		    this.getServletContext().getRequestDispatcher("/WEB-INF/pages/signOn.jsp").forward(req, resp);
		} else if(req.getParameter("action").equals("exit")) {
			session.removeAttribute("isConnected");
		    resp.sendRedirect("signIn?action=signOn");
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
		if(req.getParameter("actionPost").equals("signUp")) {
			User user = new User();
			user.setEmail(req.getParameter("email"));
			user.setNom(req.getParameter("firstName"), req.getParameter("lastName"));
			user.setPassword(req.getParameter("password"));
			
			if (userService.checkEmailUser(user.getEmail())) {
				userService.addUser(user);
				String status = "<p class='green-text text-lighten-1'>Bravo! Inscription reussie, conectez-vous.</p>";
				req.setAttribute("status", status);
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/signUp.jsp").forward(req, resp);
			} else {
				String status = "<p class='red-text text-lighten-1'>Email déjà existante</p>";
				req.setAttribute("status", status);
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/signUp.jsp").forward(req, resp);
			}
	    }
		// Connexion
		if(req.getParameter("actionPost").equals("signOn")) {
			/* Inscription de l'utilisateur */
			String email 	= req.getParameter("email");
			String password = req.getParameter("password");
			if (!userService.checkUser(email, password)) {
				/* creation de la session */
				HttpSession session = req.getSession();
		        session.setAttribute("isConnected", true);
		        session.setAttribute("userId", (userService.getUser(email)).getId());
				/* Redirection page home */
		        resp.sendRedirect("home");
			} else {
				/* Affichage de la page de connexion */
				String status = "<p class='red-text text-lighten-1'>Email ou mot de passe incorect</p>";
				req.setAttribute("status", status);
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/signOn.jsp").forward(req, resp);
			}
	    }
	}
	
}
