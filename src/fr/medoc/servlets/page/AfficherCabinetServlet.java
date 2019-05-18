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
import fr.medoc.dao.CabinetDAO;
import fr.medoc.entities.Cabinet;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherCabinet")
public class AfficherCabinetServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String JSP_PAGE = "/WEB-INF/AfficherCabinet.jsp";
	private DAOFactory daoFactory;
	private CabinetDAO cabinetDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			cabinetDao = daoFactory.getCabinetDAO();
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
		int idCabinet = (Integer) Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		if (session.getAttribute("login") != null) {
			Cabinet monCabinet = null;
			ArrayList<Cabinet> listeCabinets = null;
			try {
				listeCabinets = (ArrayList<Cabinet>) cabinetDao.findAll();
				monCabinet = cabinetDao.findByRef(idCabinet);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeCabinets", listeCabinets);
			request.setAttribute("monCabinet", monCabinet);
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} else {
			response.sendRedirect("Accueil");
		}
	}
}
