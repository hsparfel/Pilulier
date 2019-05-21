package fr.medoc.servlets.page;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.PrescriptionDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.DureeDAO;
import fr.medoc.dao.FrequenceDAO;
import fr.medoc.entities.Dose;
import fr.medoc.entities.Duree;
import fr.medoc.entities.Frequence;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Prescription;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherPrescription")
public class AfficherPrescriptionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String JSP_PAGE = "/WEB-INF/AfficherPrescription.jsp";
	private DAOFactory daoFactory;
	private PrescriptionDAO prescriptionDao;
	private UtilisateurDAO utilisateurDao;
	private MedecinDAO medecinDao;
	private MedicamentDAO medicamentDao;
	private DoseDAO doseDao;
	private FrequenceDAO frequenceDao;
	private DureeDAO dureeDao;
	

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			prescriptionDao = daoFactory.getPrescriptionDAO();
			utilisateurDao = daoFactory.getUtilisateurDAO();
			medecinDao = daoFactory.getMedecinDAO();
			medicamentDao = daoFactory.getMedicamentDAO();
			doseDao = daoFactory.getDoseDAO();
			frequenceDao = daoFactory.getFrequenceDAO();
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idPrescription = (Integer) Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		if (session.getAttribute("login") != null) {
			Prescription myPrescription = null;
			ArrayList<Prescription> listePrescriptions = null;
			ArrayList<Medecin> listeMedecins = null;
			ArrayList<Medicament> listeMedicaments = null;
			ArrayList<Dose> listeDoses = null;
			ArrayList<Frequence> listeFrequences = null;
			ArrayList<Duree> listeDurees = null;
			Utilisateur unUtilisateur = null;
			try {
				unUtilisateur = utilisateurDao.findByName((String) session.getAttribute("login"));
				listePrescriptions = (ArrayList<Prescription>) prescriptionDao.findAllByUser(unUtilisateur.getId());
				listeMedecins = (ArrayList<Medecin>) medecinDao.findAllByUser(unUtilisateur.getId());
				listeMedicaments = (ArrayList<Medicament>) medicamentDao.findAll();
				listeDoses = (ArrayList<Dose>) doseDao.findAll();
				listeFrequences = (ArrayList<Frequence>) frequenceDao.findAll();
				listeDurees = (ArrayList<Duree>) dureeDao.findAll();
				
				myPrescription = prescriptionDao.findByRef(idPrescription);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			request.setAttribute("listePrescriptions", listePrescriptions);
			request.setAttribute("listeMedecins", listeMedecins);
			request.setAttribute("listeMedicaments", listeMedicaments);
			request.setAttribute("listeDoses", listeDoses);
			request.setAttribute("listeFrequences", listeFrequences);
			request.setAttribute("listeDurees", listeDurees);
			request.setAttribute("myPrescription", myPrescription);
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} else {
			response.sendRedirect("Accueil");
		}
	}
}
