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
import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.FrequenceDAO;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.entities.Dose;
import fr.medoc.entities.Frequence;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Prescription;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;


@WebServlet("/EnregistrerPrescription")
public class EnregistrerPrescriptionServlet extends HttpServlet {

	private final String JSP_PAGE = "/WEB-INF/EnregistrerPrescription.jsp";

	private DAOFactory daoFactory;
	private PrescriptionDAO prescriptionDao;
	private UtilisateurDAO utilisateurDao;
	private DoseDAO doseDao;
	private FrequenceDAO frequenceDao;
	private MedecinDAO medecinDao;
	private MedicamentDAO medicamentDao;
	
	@Override 
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			prescriptionDao = daoFactory.getPrescriptionDAO();
			utilisateurDao = daoFactory.getUtilisateurDAO();
			doseDao = daoFactory.getDoseDAO();
			frequenceDao = daoFactory.getFrequenceDAO();
			medecinDao = daoFactory.getMedecinDAO();
			medicamentDao = daoFactory.getMedicamentDAO();
			
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
			ArrayList<Dose> listeDoses = null;
			ArrayList<Frequence> listeFrequences = null;
			ArrayList<Medecin> listeMedecins = null;
			ArrayList<Medicament> listeMedicaments = null;
			
			try {
				unUtilisateur = utilisateurDao.findByName((String) session.getAttribute("login"));
				listePrescriptions = (ArrayList<Prescription>) prescriptionDao.findAllByUser(unUtilisateur.getId());	
				listeDoses = (ArrayList<Dose>) doseDao.findAll();	
				listeFrequences = (ArrayList<Frequence>) frequenceDao.findAll();	
				listeMedecins = (ArrayList<Medecin>) medecinDao.findAllByUser(unUtilisateur.getId());	
				listeMedicaments = (ArrayList<Medicament>) medicamentDao.findAll();	
				
			
			} catch (DAOException e) {
				e.printStackTrace();
			}

			request.setAttribute("listePrescriptions", listePrescriptions);
			request.setAttribute("listeDoses", listeDoses);
			request.setAttribute("listeFrequences", listeFrequences);
			request.setAttribute("listeMedecins", listeMedecins);
			request.setAttribute("listeMedicaments", listeMedicaments);
			
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);

		}
		else {
			response.sendRedirect("Accueil");
		}
	}

}
