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
import fr.medoc.dao.ExamenDAO;
import fr.medoc.dao.OrdoExamenDAO;
import fr.medoc.entities.Examen;
import fr.medoc.entities.OrdoExamen;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/EnregistrerExamen")
public class EnregistrerExamenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String JSP_PAGE = "/WEB-INF/EnregistrerExamen.jsp";
	private DAOFactory daoFactory;
	private ExamenDAO examenDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			examenDao = daoFactory.getExamenDAO();
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
		HttpSession session = request.getSession();
		if (session.getAttribute("login") != null) {
			ArrayList<Examen> listeExamens = null;
			try {
				listeExamens = (ArrayList<Examen>) examenDao.findAll();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeExamens", listeExamens);
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} else {
			response.sendRedirect("Accueil");
		}
	}
}
