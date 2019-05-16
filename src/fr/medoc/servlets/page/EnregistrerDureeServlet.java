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
import fr.medoc.dao.DureeDAO;
import fr.medoc.entities.Duree;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/EnregistrerDuree")
public class EnregistrerDureeServlet extends HttpServlet {

	private final String JSP_PAGE = "/WEB-INF/EnregistrerDuree.jsp";

	private DAOFactory daoFactory;
	private DureeDAO dureeDao;

	@Override 
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			dureeDao = daoFactory.getDureeDAO();
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

			ArrayList<Duree> listeDurees = null;
			try {
				listeDurees = (ArrayList<Duree>) dureeDao.findAll();	
			} catch (DAOException e) {
				e.printStackTrace();
			}

			request.setAttribute("listeDurees", listeDurees);

			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);

		}
		else {
			response.sendRedirect("Accueil");
		}
	}

}
