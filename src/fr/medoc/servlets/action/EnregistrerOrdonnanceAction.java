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

import fr.medoc.dao.OrdonnanceDAO;
import fr.medoc.dao.Prescription2DAO;
import fr.medoc.dao.PriseDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.AnalyseDAO;
import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.ExamenDAO;
import fr.medoc.entities.Ordonnance;
import fr.medoc.entities.Prescription2;
import fr.medoc.entities.Prise;
import fr.medoc.entities.Utilisateur;
import fr.medoc.enumeration.EnumAnalyse;
import fr.medoc.enumeration.EnumDuree;
import fr.medoc.enumeration.EnumExamen;
import fr.medoc.entities.Analyse;
import fr.medoc.entities.Cabinet;
import fr.medoc.entities.Dose;
import fr.medoc.entities.Examen;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Medicament;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/EnregistrerOrdonnanceAction")
public class EnregistrerOrdonnanceAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private OrdonnanceDAO ordonnanceDao;
	private Prescription2DAO prescriptionDao;
	private AnalyseDAO analyseDao;
	private ExamenDAO examenDao;
	private MedecinDAO medecinDAO;
	private MedicamentDAO medicamentDAO;
	private DoseDAO doseDAO;
	private UtilisateurDAO utilisateurDAO;
	private CabinetDAO cabinetDAO;
	private PriseDAO priseDAO;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			ordonnanceDao = daoFactory.getOrdonnanceDAO();
			medecinDAO = daoFactory.getMedecinDAO();
			doseDAO = daoFactory.getDoseDAO();
			medicamentDAO = daoFactory.getMedicamentDAO();
			utilisateurDAO = daoFactory.getUtilisateurDAO();
			examenDao = daoFactory.getExamenDAO();
			cabinetDAO = daoFactory.getCabinetDAO();
			analyseDao = daoFactory.getAnalyseDAO();
			priseDAO = daoFactory.getPriseDAO();
			prescriptionDao = daoFactory.getPrescription2DAO();
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

		//enregistrement de l'ordonnance (base)
		HttpSession session = request.getSession();
		Utilisateur unUtilisateur = null;
		int idMedecin = (Integer) Integer.parseInt(request.getParameter("idMedecin"));
		String date = request.getParameter("date");
		String commentaire = request.getParameter("commentaire");
		Medecin unMedecin = null;
		Ordonnance nouvelleOrdonnance = new Ordonnance();
		try {
			unUtilisateur = utilisateurDAO.findByName((String) session.getAttribute("login"));
			unMedecin = medecinDAO.findByRef(idMedecin);

			nouvelleOrdonnance.setUtilisateur(unUtilisateur);
			nouvelleOrdonnance.setMedecin(unMedecin);
			nouvelleOrdonnance.setDate(date);
			nouvelleOrdonnance.setCommentaire(commentaire);
			ordonnanceDao.ajouterOrdonnance(nouvelleOrdonnance);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		//enregistrement des prescriptions

		int nbPrescription = (Integer) Integer.parseInt(request.getParameter("nbPrescription"));
		if (nbPrescription>0) {

			for (int i=1;i<=nbPrescription;i++) {

				int idMedicament = (Integer) Integer.parseInt(request.getParameter("idMedicament"+i));
				Float nbDose = (Float) Float.parseFloat(request.getParameter("quantiteDose"+i));
				int idDose = (Integer) Integer.parseInt(request.getParameter("idDose"+i));
				int nbFrequence = (Integer) Integer.parseInt(request.getParameter("quantiteFrequence"+i));
				String idFrequence = request.getParameter("idFrequence"+i);
				int nbDuree = (Integer) Integer.parseInt(request.getParameter("nbDuree"+i));
				String idDuree = request.getParameter("idDuree"+i);
				String commentairePrescription = request.getParameter("commentairePrescription"+i);
				String dateDebut = request.getParameter("date");
				int frequenceRadio = 0;
				int frequenceCheckbox_0 = 0;
				int frequenceCheckbox_1 = 0;
				int frequenceCheckbox_2 = 0;
				Medicament unMedicament = null;
				Dose uneDose = null;
				try {
					unMedicament = medicamentDAO.findByRef(idMedicament);
					uneDose = doseDAO.findByRef(idDose);
					Prescription2 nouvellePrescription = new Prescription2();

					nouvellePrescription.setOrdonnance(nouvelleOrdonnance);
					nouvellePrescription.setMedicament(unMedicament);
					nouvellePrescription.setNbDose(nbDose);
					nouvellePrescription.setDose(uneDose);
					nouvellePrescription.setNbFrequence(nbFrequence);
					nouvellePrescription.setFrequence(EnumDuree.valueOf(idFrequence));
					nouvellePrescription.setNbDuree(nbDuree);
					nouvellePrescription.setDuree(EnumDuree.valueOf(idDuree));
					nouvellePrescription.setDateDebut(dateDebut);
					nouvellePrescription.setCommentaire(commentairePrescription);

					nouvellePrescription.setDateFin(nouvellePrescription.calculerDateFin(nouvellePrescription.getDateDebut(), nouvellePrescription.getNbDuree(), nouvellePrescription.getDuree()));


					if (request.getParameter("frequenceRadio"+i) != null) {
						frequenceRadio = (Integer) Integer.parseInt(request.getParameter("frequenceRadio"+i));
					}
					switch (frequenceRadio) {
					case 1: {
						nouvellePrescription.setMatin(1);
						break;
					}
					case 2: {
						nouvellePrescription.setMidi(1);
						break;
					}
					case 3: {
						nouvellePrescription.setSoir(1);
						break;
					}
					}
					if (request.getParameter("frequenceCheckbox"+i+"_0") != null) {
						frequenceCheckbox_0 = (Integer) Integer.parseInt(request.getParameter("frequenceCheckbox"+i+"_0"));
					}
					if (request.getParameter("frequenceCheckbox"+i+"_1") != null) {
						frequenceCheckbox_1 = (Integer) Integer.parseInt(request.getParameter("frequenceCheckbox"+i+"_1"));
					}
					if (request.getParameter("frequenceCheckbox"+i+"_2") != null) {
						frequenceCheckbox_2 = (Integer) Integer.parseInt(request.getParameter("frequenceCheckbox"+i+"_2"));
					}
					if (frequenceCheckbox_0 == 1) {
						nouvellePrescription.setMatin(1);
					}
					if (frequenceCheckbox_1 == 2) {
						nouvellePrescription.setMidi(1);
					}
					if (frequenceCheckbox_2 == 3) {
						nouvellePrescription.setSoir(1);
					}
					if (nbFrequence==3 && idFrequence.contentEquals("JOU")) {
						nouvellePrescription.setMatin(1);
						nouvellePrescription.setMidi(1);
						nouvellePrescription.setSoir(1);
					}
					prescriptionDao.ajouterPrescription(nouvellePrescription);


					//enregistrement des prises
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate dateDebutParsedDate = LocalDate.parse(nouvellePrescription.getDateDebut(), formatter);
					LocalDate dateFinParsedDate = LocalDate.parse(nouvellePrescription.getDateFin(), formatter);
					Prise newPrise = new Prise();
					System.out.println("ici1");
System.out.println(dateDebutParsedDate);
System.out.println(nouvellePrescription.getFrequence());
System.out.println(dateFinParsedDate);
					System.out.println("ici2");
					for (LocalDate dateLocale = dateDebutParsedDate; dateLocale.isBefore(dateFinParsedDate); dateLocale =  (idFrequence.contentEquals("JOU") ? dateLocale.plusDays(1) : (idFrequence.contentEquals("SEM")? dateLocale.plusWeeks(1) : dateLocale.plusMonths(1)))) {
						System.out.println("ici3");
						newPrise.setPrescription(nouvellePrescription);
						newPrise.setDate(dateLocale.format(formatter));
						if (nouvellePrescription.getMatin() == 1 || nouvellePrescription.getMidi() == 1
								|| nouvellePrescription.getSoir() == 1) {
							System.out.println("ici4");
							if (nouvellePrescription.getMatin() == 1) {
								newPrise.setHeure("08:00");
								priseDAO.ajouterPrise(newPrise);
							}
							if (nouvellePrescription.getMidi() == 1) {
								newPrise.setHeure("12:00");
								priseDAO.ajouterPrise(newPrise);
							}
							if (nouvellePrescription.getSoir() == 1) {
								newPrise.setHeure("19:00");
								priseDAO.ajouterPrise(newPrise);
							}
						} else if (nouvellePrescription.getNbFrequence()==3 && idFrequence.contentEquals("JOU")){
							System.out.println("ici5");
							newPrise.setHeure("08:00");
							priseDAO.ajouterPrise(newPrise);
							newPrise.setHeure("12:00");
							priseDAO.ajouterPrise(newPrise);
							newPrise.setHeure("19:00");
							priseDAO.ajouterPrise(newPrise);

						} else if (nouvellePrescription.getNbFrequence()==4 && idFrequence.contentEquals("JOU")){
							System.out.println("ici6");
							newPrise.setHeure("08:00");
							priseDAO.ajouterPrise(newPrise);
							newPrise.setHeure("12:00");
							priseDAO.ajouterPrise(newPrise);
							newPrise.setHeure("19:00");
							priseDAO.ajouterPrise(newPrise);
							newPrise.setHeure("22:00");
							priseDAO.ajouterPrise(newPrise);
						} else {
							System.out.println("ici7");
							newPrise.setHeure("08:00");
							priseDAO.ajouterPrise(newPrise);
						}

					}
					//String dateFin = dateFinParsedDate.format(formatter);
				} catch (DAOException e) {
					e.printStackTrace();
				}



			}
		}

		//enregistrement des analyses

		int nbAnalyse = (Integer) Integer.parseInt(request.getParameter("nbAnalyse"));
		if (nbAnalyse>0) {

			for (int i=1;i<=nbAnalyse;i++) {

				int idCabinetAnalyse = (Integer) Integer.parseInt(request.getParameter("idCabinetAnalyse"+i));
				String nomAnalyse = request.getParameter("idAnalyse"+i);
				String dateAnalyse = request.getParameter("dateAnalyse"+i);
				String commentaireAnalyse = request.getParameter("commentaireAnalyse"+i);
				Cabinet unCabinet = null;
				try {
					unCabinet = cabinetDAO.findByRef(idCabinetAnalyse);
					Analyse nouvelleAnalyse = new Analyse();
					nouvelleAnalyse.setOrdonnance(nouvelleOrdonnance);
					nouvelleAnalyse.setNom(EnumAnalyse.valueOf(nomAnalyse));
					nouvelleAnalyse.setCabinet(unCabinet);
					nouvelleAnalyse.setDate(dateAnalyse);
					nouvelleAnalyse.setCommentaire(commentaireAnalyse);
					analyseDao.ajouterAnalyse(nouvelleAnalyse);
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}}

		//enregistrement des examens

		int nbExamen = (Integer) Integer.parseInt(request.getParameter("nbExamen"));
		if (nbExamen>0) {

			for (int i=1;i<=nbExamen;i++) {

				int idCabinetExamen = (Integer) Integer.parseInt(request.getParameter("idCabinetExamen"+i));
				String nomExamen = request.getParameter("idExamen"+i);
				String dateExamen = request.getParameter("dateExamen"+i);
				String commentaireExamen = request.getParameter("commentaireExamen"+i);
				Cabinet unCabinet = null;
				try {
					unCabinet = cabinetDAO.findByRef(idCabinetExamen);
					Examen nouvelExamen = new Examen();
					nouvelExamen.setOrdonnance(nouvelleOrdonnance);
					nouvelExamen.setNom(EnumExamen.valueOf(nomExamen));
					nouvelExamen.setCabinet(unCabinet);
					nouvelExamen.setDate(dateExamen);
					nouvelExamen.setCommentaire(commentaireExamen);
					examenDao.ajouterExamen(nouvelExamen);
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}}



		response.sendRedirect("EnregistrerOrdonnance");
	}
}
