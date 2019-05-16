package fr.medoc.servlets.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Deconnexion")
public class DeconnexionAction extends HttpServlet {
	
	//private final String JSP_PAGE = "/WEB-INF/Accueil.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
		response.sendRedirect("Accueil");
		//this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
	}



}
