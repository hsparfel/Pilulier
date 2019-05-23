package fr.medoc.servlets.action;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.PrescriptionDAO;
import fr.medoc.dao.PriseDAO;
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
import fr.medoc.entities.Prise;
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
	private PriseDAO priseDAO;

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
			priseDAO = daoFactory.getPriseDAO();
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
		int frequenceRadio = 0;
		int frequenceCheckbox_0 = 0;
		int frequenceCheckbox_1 = 0;
		int frequenceCheckbox_2 = 0;
		int qteDuree = (Integer) Integer.parseInt(request.getParameter("nbDuree"));
		int idDuree = (Integer) Integer.parseInt(request.getParameter("idDuree"));
		String dateDebut = request.getParameter("date");
		Dose uneDose = null;
		Duree uneDuree = null;
		Frequence uneFrequence = null;
		Medecin unMedecin = null;
		Medicament unMedicament = null;

		try {
			unUtilisateur = utilisateurDAO.findByName((String) session.getAttribute("login"));
			uneDose = doseDAO.findByRef(idDose);
			uneDuree = dureeDAO.findByRef(idDuree);
			uneFrequence = frequenceDAO.findByRef(idFrequence);
			unMedecin = medecinDAO.findByRef(idMedecin);
			unMedicament = medicamentDAO.findByRef(idMedicament);

			Prescription nouveauPrescription = new Prescription(unUtilisateur, unMedecin, unMedicament, qteDose,
					uneDose, qteFrequence, uneFrequence, qteDuree, uneDuree, dateDebut);
			nouveauPrescription.setDateFin(nouveauPrescription.calculerDateFin(nouveauPrescription.getDateDebut(),
					nouveauPrescription.getNbDuree(), nouveauPrescription.getDuree()));
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
			prescriptionDao.ajouterPrescription(nouveauPrescription);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dateDebutParsedDate = LocalDate.parse(nouveauPrescription.getDateDebut(), formatter);
			LocalDate dateFinParsedDate = LocalDate.parse(nouveauPrescription.getDateFin(), formatter);
			Prise newPrise = new Prise();
		//sauvegarde au cas où
			//for (LocalDate date = dateDebutParsedDate; date.isBefore(dateFinParsedDate); date = date.plusDays(1)) {
			/*System.out.println(nouveauPrescription.getFrequence().getNom());
			if (nouveauPrescription.getFrequence().getNom().equals("jour")) {
				System.out.println("=>jour");
			} else if (nouveauPrescription.getFrequence().getNom().equals("semaine")) {
				System.out.println("=>semaine");
			} else  {
				System.out.println("=>mois");
			}*/
			for (LocalDate date = dateDebutParsedDate; date.isBefore(dateFinParsedDate); date =  (nouveauPrescription.getFrequence().getNom().equals("jour") ? date.plusDays(1) : (nouveauPrescription.getFrequence().getNom().equals("semaine") ? date.plusWeeks(1) : date.plusMonths(1)))) {
				newPrise.setPrescription(nouveauPrescription);
				newPrise.setDate(date.format(formatter));
				if (nouveauPrescription.getMatin() == 1 || nouveauPrescription.getMatin() == 1
						|| nouveauPrescription.getMatin() == 1) {
					if (nouveauPrescription.getMatin() == 1) {
						newPrise.setHeure("matin");
						priseDAO.ajouterPrise(newPrise);
					}
					if (nouveauPrescription.getMidi() == 1) {
						newPrise.setHeure("midi");
						priseDAO.ajouterPrise(newPrise);
					}
					if (nouveauPrescription.getSoir() == 1) {
						newPrise.setHeure("soir");
						priseDAO.ajouterPrise(newPrise);
					}
				} else {
					newPrise.setHeure("matin");
					priseDAO.ajouterPrise(newPrise);
				}
			}
			String dateFin = dateFinParsedDate.format(formatter);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect("EnregistrerPrescription");
	}
}
