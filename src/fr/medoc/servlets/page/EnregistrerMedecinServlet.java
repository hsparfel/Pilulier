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
import fr.medoc.dao.SpecialiteDAO;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Specialite;
import fr.medoc.entities.Cabinet;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;


@WebServlet("/EnregistrerMedecin")
public class EnregistrerMedecinServlet extends HttpServlet {

	private final String JSP_PAGE = "/WEB-INF/EnregistrerMedecin.jsp";

	private DAOFactory daoFactory;
	private MedecinDAO medecinDao;
	private UtilisateurDAO utilisateurDao;
	private SpecialiteDAO specialiteDao;
	private CabinetDAO cabinetDao;
	
	@Override 
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			medecinDao = daoFactory.getMedecinDAO();
			utilisateurDao = daoFactory.getUtilisateurDAO();
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session.getAttribute("login") != null) {

			ArrayList<Medecin> listeMedecins = null;
			ArrayList<Cabinet> listeCabinets = null;
			ArrayList<Specialite> listeSpecialites = null;
			Utilisateur unUtilisateur = null;
			
			try {
				unUtilisateur = utilisateurDao.findByName((String) session.getAttribute("login"));
				listeMedecins = (ArrayList<Medecin>) medecinDao.findAll();
				listeCabinets = (ArrayList<Cabinet>) cabinetDao.findAll();
				listeSpecialites = (ArrayList<Specialite>) specialiteDao.findAll();
				
			} catch (DAOException e) {
				e.printStackTrace();
			}

			request.setAttribute("listeMedecins", listeMedecins);
			request.setAttribute("listeCabinets", listeCabinets);
			request.setAttribute("listeSpecialites", listeSpecialites);

			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);

		}
		else {
			response.sendRedirect("Accueil");
		}
	}

}
