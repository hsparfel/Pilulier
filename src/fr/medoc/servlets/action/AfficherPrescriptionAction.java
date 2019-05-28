package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.PrescriptionDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.DureeDAO;
import fr.medoc.dao.FrequenceDAO;
import fr.medoc.entities.Utilisateur;
import fr.medoc.entities.Dose;
import fr.medoc.entities.Duree;
import fr.medoc.entities.Frequence;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Prescription;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherPrescriptionAction")
public class AfficherPrescriptionAction extends HttpServlet {
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
		int idPrescription = (Integer) Integer.parseInt(request.getParameter("idPrescription"));
		HttpSession session = request.getSession();
		Utilisateur unUtilisateur = null;
		String submitForm = request.getParameter("submit");

		try {

			if (submitForm != null) {
				if ("Valider".equals(submitForm)) {
					String dateDebut = request.getParameter("date");

					int frequenceRadio = 0;
					int frequenceCheckbox_0 = 0;
					int frequenceCheckbox_1 = 0;
					int frequenceCheckbox_2 = 0;

					Dose uneDose = null;
					Duree uneDuree = null;
					Frequence uneFrequence = null;
					Medecin unMedecin = null;
					Medicament unMedicament = null;
					unUtilisateur = utilisateurDAO.findByName((String) session.getAttribute("login"));
					int idMedecin = (Integer) Integer.parseInt(request.getParameter("idMedecin"));
					int idMedicament = (Integer) Integer.parseInt(request.getParameter("idMedicament"));
					float qteDose = (Float) Float.parseFloat(request.getParameter("quantiteDose"));
					int idDose = (Integer) Integer.parseInt(request.getParameter("idDose"));
					int qteFrequence = (Integer) Integer.parseInt(request.getParameter("quantiteFrequence"));
					int idFrequence = (Integer) Integer.parseInt(request.getParameter("idFrequence"));
					int qteDuree = (Integer) Integer.parseInt(request.getParameter("nbDuree"));
					int idDuree = (Integer) Integer.parseInt(request.getParameter("idDuree"));
					uneDose = doseDAO.findByRef(idDose);
					uneDuree = dureeDAO.findByRef(idDuree);
					uneFrequence = frequenceDAO.findByRef(idFrequence);
					unMedecin = medecinDAO.findByRef(idMedecin);
					unMedicament = medicamentDAO.findByRef(idMedicament);
					Prescription nouveauPrescription = new Prescription(unUtilisateur, unMedecin, unMedicament, qteDose,
							uneDose, qteFrequence, uneFrequence, qteDuree, uneDuree, dateDebut);
					// ajout de la date de fin
					nouveauPrescription
							.setDateFin(nouveauPrescription.calculerDateFin(nouveauPrescription.getDateDebut(),
									nouveauPrescription.getNbDuree(), nouveauPrescription.getDuree()));

					// rajouter ici les setters matin midi soir
					if (request.getParameter("frequenceRadio") != null) {
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

					if (request.getParameter("frequenceCheckbox_0") != null) {
						frequenceCheckbox_0 = (Integer) Integer.parseInt(request.getParameter("frequenceCheckbox_0"));
					}
					if (request.getParameter("frequenceCheckbox_1") != null) {
						frequenceCheckbox_1 = (Integer) Integer.parseInt(request.getParameter("frequenceCheckbox_1"));
					}
					if (request.getParameter("frequenceCheckbox_2") != null) {
						frequenceCheckbox_2 = (Integer) Integer.parseInt(request.getParameter("frequenceCheckbox_2"));
					}
					if (frequenceCheckbox_0 == 1) {
						nouveauPrescription.setMatin(1);
					}
					if (frequenceCheckbox_1 == 2) {
						nouveauPrescription.setMidi(1);
					}
					if (frequenceCheckbox_2 == 3) {
						nouveauPrescription.setSoir(1);
					}
					// Prescription nouveauPrescription = new
					// Prescription(unUtilisateur,unMedecin,unMedicament,qteDose,idDose,qteFrequence,
					// idFrequence,qteDuree,idDuree,dateDebut);
					prescriptionDao.modifierPrescription(nouveauPrescription, idPrescription);
				}
				if ("Supprimer".equals(submitForm)) {
					prescriptionDao.supprimerPrescription(idPrescription);
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect("EnregistrerPrescription");
	}
}
