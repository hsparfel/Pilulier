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
import fr.medoc.dao.FrequenceDAO;
import fr.medoc.entities.Frequence;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/EnregistrerFrequence")
public class EnregistrerFrequenceServlet extends HttpServlet {

	private final String JSP_PAGE = "/WEB-INF/EnregistrerFrequence.jsp";

	private DAOFactory daoFactory;
	private FrequenceDAO frequenceDao;

	@Override 
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			frequenceDao = daoFactory.getFrequenceDAO();
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session.getAttribute("login") != null) {

			ArrayList<Frequence> listeFrequences = null;
			try {
				listeFrequences = (ArrayList<Frequence>) frequenceDao.findAll();	
			} catch (DAOException e) {
				e.printStackTrace();
			}

			request.setAttribute("listeFrequences", listeFrequences);

			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);

		}
		else {
			response.sendRedirect("Accueil");
		}
	}

}
