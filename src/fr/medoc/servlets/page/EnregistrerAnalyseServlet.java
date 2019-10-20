package fr.medoc.servlets.page;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.AnalyseDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.OrdoAnalyseDAO;
import fr.medoc.entities.Analyse;
import fr.medoc.entities.OrdoAnalyse;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/EnregistrerAnalyse")
public class EnregistrerAnalyseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String JSP_PAGE = "/WEB-INF/EnregistrerAnalyse.jsp";
	private DAOFactory daoFactory;
	private AnalyseDAO analyseDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			analyseDao = daoFactory.getAnalyseDAO();
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
			ArrayList<Analyse> listeAnalyses = null;
			try {
				listeAnalyses = (ArrayList<Analyse>) analyseDao.findAll();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeAnalyses", listeAnalyses);
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} else {
			response.sendRedirect("Accueil");
		}
	}
}
