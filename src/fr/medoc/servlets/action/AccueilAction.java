package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;


@WebServlet("/AccueilAction")
public class AccueilAction extends HttpServlet {
	private DAOFactory daoFactory;
	private UtilisateurDAO utilisateurDao;
	//private final String JSP_PAGE = "/WEB-INF/Accueil.jsp";
	private final String JSP_PAGE = "/Accueil";
	
	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			utilisateurDao = daoFactory.getUtilisateurDAO();
		} catch (DAOConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		try {
			daoFactory.closeConnexion(daoFactory.getConnection());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomUtilisateur = request.getParameter("nomUtilisateur");
			
		Utilisateur nouvelUtilisateur = new Utilisateur(nomUtilisateur);
		
		
		
		try {
			//System.out.println(utilisateurDao.findByName(nomUtilisateur));
		//	if (utilisateurDao.findByName(nomUtilisateur)==null) {
			utilisateurDao.ajouterUtilisateur(nouvelUtilisateur);//}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		//request.setAttribute("login",nomUtilisateur);
		HttpSession session = request.getSession(true);
		session.setAttribute("login",nomUtilisateur);
		
		
		
		//this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		response.sendRedirect("ModifUserProfil");
	}

}
