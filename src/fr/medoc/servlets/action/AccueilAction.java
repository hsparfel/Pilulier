package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AccueilAction")
public class AccueilAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomUtilisateur = request.getParameter("nomUtilisateur");
		HttpSession session = request.getSession(true);
		session.setAttribute("login", nomUtilisateur);
		response.sendRedirect("ModifUserProfil");
	}
}
