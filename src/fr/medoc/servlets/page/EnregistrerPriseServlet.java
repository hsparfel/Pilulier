package fr.medoc.servlets.page;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.PriseDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Prise;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/EnregistrerPrise")
public class EnregistrerPriseServlet extends HttpServlet {

	private final String JSP_PAGE = "/WEB-INF/EnregistrerPrise.jsp";

	private DAOFactory daoFactory;
	private PriseDAO priseDao;

	@Override 
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			priseDao = daoFactory.getPriseDAO();
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

			ArrayList<Prise> listePrises = null;
			try {
				listePrises = (ArrayList<Prise>) priseDao.findAll();	
			} catch (DAOException e) {
				e.printStackTrace();
			}

			request.setAttribute("listePrises", listePrises);

			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);

		}
		else {
			response.sendRedirect("Accueil");
		}
	}

}
