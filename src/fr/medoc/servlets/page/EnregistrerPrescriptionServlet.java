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
import fr.medoc.dao.PrescriptionDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Prescription;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

/**
 * Servlet implementation class EnregistrerCourtsServlet
 */
@WebServlet("/EnregistrerPrescription")
public class EnregistrerPrescriptionServlet extends HttpServlet {

	private final String JSP_PAGE = "/WEB-INF/EnregistrerPrescription.jsp";

	private DAOFactory daoFactory;
	private PrescriptionDAO prescriptionDao;
	private UtilisateurDAO utilisateurDao;
	
	@Override 
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			prescriptionDao = daoFactory.getPrescriptionDAO();
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session.getAttribute("login") != null) {

			ArrayList<Prescription> listePrescriptions = null;
			Utilisateur unUtilisateur = null;
			
			try {
				unUtilisateur = utilisateurDao.findByName((String) session.getAttribute("login"));
				listePrescriptions = (ArrayList<Prescription>) prescriptionDao.findAllByUser(unUtilisateur.getId());	
			} catch (DAOException e) {
				e.printStackTrace();
			}

			request.setAttribute("listePrescriptions", listePrescriptions);

			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);

		}
		else {
			response.sendRedirect("Accueil");
		}
	}

}