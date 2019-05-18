package fr.medoc.servlets.page;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherUtilisateur")
public class AfficherUtilisateurServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String JSP_PAGE = "/WEB-INF/AfficherUtilisateur.jsp";
	private DAOFactory daoFactory;
	private UtilisateurDAO utilisateurDao;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idUtilisateur = (Integer) Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		if (session.getAttribute("login") != null) {
			Utilisateur maUtilisateur = null;
			ArrayList<Utilisateur> listeUtilisateurs = null;
			try {
				listeUtilisateurs = (ArrayList<Utilisateur>) utilisateurDao.findAll();
				maUtilisateur = utilisateurDao.findByRef(idUtilisateur);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeUtilisateurs", listeUtilisateurs);
			request.setAttribute("maUtilisateur", maUtilisateur);
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} else {
			response.sendRedirect("Accueil");
		}
	}
}
