package fr.medoc.servlets.page;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.SpecialiteDAO;
import fr.medoc.entities.Cabinet;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Specialite;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherMedecin")
public class AfficherMedecinServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String JSP_PAGE = "/WEB-INF/AfficherMedecin.jsp";
	private DAOFactory daoFactory;
	private MedecinDAO medecinDao;
	private SpecialiteDAO specialiteDao;
	private CabinetDAO cabinetDao;
	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			medecinDao = daoFactory.getMedecinDAO();
			specialiteDao = daoFactory.getSpecialiteDAO();
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
		int idMedecin = (Integer) Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		if (session.getAttribute("login") != null) {
			Medecin monMedecin = null;
			ArrayList<Medecin> listeMedecins = null;
			ArrayList<Specialite> listeSpecialites = null;
			ArrayList<Cabinet> listeCabinets = null;
			try {
				listeMedecins = (ArrayList<Medecin>) medecinDao.findAll();
				listeSpecialites = (ArrayList<Specialite>) specialiteDao.findAll();
				listeCabinets = (ArrayList<Cabinet>) cabinetDao.findAll();
				monMedecin = medecinDao.findByRef(idMedecin);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeMedecins", listeMedecins);
			request.setAttribute("listeSpecialites", listeSpecialites);
			request.setAttribute("listeCabinets", listeCabinets);
			request.setAttribute("monMedecin", monMedecin);
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} else {
			response.sendRedirect("Accueil");
		}
	}
}
