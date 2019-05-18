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
import fr.medoc.dao.SpecialiteDAO;
import fr.medoc.entities.Specialite;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherSpecialite")
public class AfficherSpecialiteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String JSP_PAGE = "/WEB-INF/AfficherSpecialite.jsp";
	private DAOFactory daoFactory;
	private SpecialiteDAO specialiteDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			specialiteDao = daoFactory.getSpecialiteDAO();
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
		int idSpecialite = (Integer) Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		if (session.getAttribute("login") != null) {
			Specialite maSpecialite = null;
			ArrayList<Specialite> listeSpecialites = null;
			try {
				listeSpecialites = (ArrayList<Specialite>) specialiteDao.findAll();
				maSpecialite = specialiteDao.findByRef(idSpecialite);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeSpecialites", listeSpecialites);
			request.setAttribute("maSpecialite", maSpecialite);
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} else {
			response.sendRedirect("Accueil");
		}
	}
}
