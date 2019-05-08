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
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.FrequenceDAO;
import fr.medoc.entities.Dose;
import fr.medoc.entities.Frequence;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Prescription;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/ModifUserProfilAction")
public class ModifUserProfilAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private PrescriptionDAO prescriptionDao;
	private UtilisateurDAO utilisateurDAO;
	private MedicamentDAO medicamentDAO;
	private DoseDAO doseDAO;
	private FrequenceDAO frequenceDAO;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			prescriptionDao = daoFactory.getPrescriptionDAO();
			utilisateurDAO = daoFactory.getUtilisateurDAO();
			medicamentDAO = daoFactory.getMedicamentDAO();
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
		int idMedicament = (Integer) Integer.parseInt(request.getParameter("idmedicament"));
		int nbDose = (Integer) Integer.parseInt(request.getParameter("posologieQuantite"));
		int idDose = (Integer) Integer.parseInt(request.getParameter("posologieDose"));
		int nbFrequence = (Integer) Integer.parseInt(request.getParameter("posologieFrequence"));
		int idFrequence = (Integer) Integer.parseInt(request.getParameter("posologieTypeFrequence"));
		HttpSession session = request.getSession();
		Utilisateur unUtilisateur = null;
		Medicament unMedicament = null;
		Dose uneDose = null;
		Frequence uneFrequence = null;
		try {
			unUtilisateur = utilisateurDAO.findByName((String) session.getAttribute("login"));
			unMedicament = medicamentDAO.findByRef(idMedicament);
			uneDose = doseDAO.findByRef(idDose);
			uneFrequence = frequenceDAO.findByRef(idFrequence);
			Prescription unePrescription = new Prescription(unUtilisateur, unMedicament, nbDose, uneDose, nbFrequence,
					uneFrequence);
			prescriptionDao.ajouterPrescription(unePrescription);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect("ModifUserProfil");
	}
}
