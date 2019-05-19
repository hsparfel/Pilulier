package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.PrescriptionDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.FrequenceDAO;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.DureeDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Dose;
import fr.medoc.entities.Duree;
import fr.medoc.entities.Prescription;
import fr.medoc.entities.Utilisateur;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Frequence;
import fr.medoc.entities.Medecin;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/EnregistrerPrescriptionAction")
public class EnregistrerPrescriptionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private PrescriptionDAO prescriptionDao;
	private DoseDAO doseDAO;
	private DureeDAO dureeDAO;
	private FrequenceDAO frequenceDAO;
	private MedicamentDAO medicamentDAO;
	private MedecinDAO medecinDAO;
	private UtilisateurDAO utilisateurDAO;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			prescriptionDao = daoFactory.getPrescriptionDAO();
			doseDAO = daoFactory.getDoseDAO();
			dureeDAO = daoFactory.getDureeDAO();
			frequenceDAO = daoFactory.getFrequenceDAO();
			medicamentDAO = daoFactory.getMedicamentDAO();
			medecinDAO = daoFactory.getMedecinDAO();
			utilisateurDAO = daoFactory.getUtilisateurDAO();
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur unUtilisateur = null;
		int idMedecin = (Integer) Integer.parseInt(request.getParameter("idMedecin"));
		int idMedicament = (Integer) Integer.parseInt(request.getParameter("idMedicament"));
		int qteDose = (Integer) Integer.parseInt(request.getParameter("quantiteDose"));
		int idDose = (Integer) Integer.parseInt(request.getParameter("idDose"));
		int qteFrequence = (Integer) Integer.parseInt(request.getParameter("quantiteFrequence"));
		int idFrequence = (Integer) Integer.parseInt(request.getParameter("idFrequence"));

		int frequenceRadio=0;
		
		int frequenceCheckbox_0=0;
		int frequenceCheckbox_1=0;
		int frequenceCheckbox_2=0;

		int qteDuree = (Integer) Integer.parseInt(request.getParameter("nbDuree"));
		int idDuree = (Integer) Integer.parseInt(request.getParameter("idDuree"));
		String date = request.getParameter("date");
		
		Dose uneDose = null;
		Duree uneDuree = null;
		Frequence uneFrequence = null;
		Medecin unMedecin = null;
		Medicament unMedicament = null;

		// recup des radio et checkbox
		System.out.println("radio: " + request.getParameter("frequenceRadio"));
		System.out.println("check0: " + request.getParameter("frequenceCheckbox_0"));
		System.out.println("check1: " + request.getParameter("frequenceCheckbox_1"));
		System.out.println("check2: " + request.getParameter("frequenceCheckbox_2"));
		try {
			unUtilisateur = utilisateurDAO.findByName((String) session.getAttribute("login"));
			uneDose = doseDAO.findByRef(idDose);
			uneDuree = dureeDAO.findByRef(idDuree);
			uneFrequence = frequenceDAO.findByRef(idFrequence);
			unMedecin = medecinDAO.findByRef(idMedecin);
			unMedicament = medicamentDAO.findByRef(idMedicament);
			Prescription nouveauPrescription = new Prescription(unUtilisateur, unMedecin, unMedicament, qteDose,
					uneDose, qteFrequence, uneFrequence, qteDuree,
					uneDuree, date);
			// rajouter ici les setters matin midi soir
			if (request.getParameter("frequenceRadio")!=null) {
			frequenceRadio = (Integer) Integer.parseInt(request.getParameter("frequenceRadio"));
			}
			switch (frequenceRadio) {
			case 1: {
				nouveauPrescription.setMatin(1);
				break;
			}
			case 2: {
				
				nouveauPrescription.setMidi(1);
				break;
			}
			case 3: {
				
				nouveauPrescription.setSoir(1);
				break;
			}
			}
			
			if (request.getParameter("frequenceCheckbox_0")!=null) {
				frequenceCheckbox_0 = (Integer) Integer.parseInt(request.getParameter("frequenceCheckbox_0"));
				}
			if (request.getParameter("frequenceCheckbox_1")!=null) {
				frequenceCheckbox_1 = (Integer) Integer.parseInt(request.getParameter("frequenceCheckbox_1"));
				}
			if (request.getParameter("frequenceCheckbox_2")!=null) {
				frequenceCheckbox_2 = (Integer) Integer.parseInt(request.getParameter("frequenceCheckbox_2"));
				}
			if (frequenceCheckbox_0==1) {
				nouveauPrescription.setMatin(1);
			}
			if (frequenceCheckbox_1==2) {
				nouveauPrescription.setMidi(1);
			}
			if (frequenceCheckbox_2==3) {
				nouveauPrescription.setSoir(1);
			}
			
			
			prescriptionDao.ajouterPrescription(nouveauPrescription);
			
			//ajouter ici l'enregistrement de toutes les prises necessaires à la prescription
			
			
			
			//
			
			
			
		} catch (DAOException e) {
			e.printStackTrace();
		}

		response.sendRedirect("EnregistrerPrescription");

	}

}
