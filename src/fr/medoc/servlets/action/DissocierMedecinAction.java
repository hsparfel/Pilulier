package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.PatientMedecinDAO;
import fr.medoc.dao.RdvDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.PatientMedecin;
import fr.medoc.entities.Utilisateur;
import fr.medoc.entities.Medecin;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/DissocierMedecinAction")
public class DissocierMedecinAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private PatientMedecinDAO patientMedecinDao;
	private MedecinDAO medecinDAO;
	private UtilisateurDAO utilisateurDAO;
	private RdvDAO rdvDAO;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			patientMedecinDao = daoFactory.getPatientMedecinDAO();
			medecinDAO = daoFactory.getMedecinDAO();
			utilisateurDAO = daoFactory.getUtilisateurDAO();
			rdvDAO = daoFactory.getRdvDAO();
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

		Medecin unMedecin = null;

		try {
			unUtilisateur = utilisateurDAO.findByName((String) session.getAttribute("login"));
			unMedecin = medecinDAO.findByRef(idMedecin);
			PatientMedecin nouveauPatientMedecin = new PatientMedecin(unUtilisateur, unMedecin);
			patientMedecinDao.supprimerPatientMedecin(nouveauPatientMedecin);
			//ajouter ici la suppresson des rdv comportant ce PatientMEdecin
			rdvDAO.supprimerRdvByUserAndMedecin(nouveauPatientMedecin);
						//
		} catch (DAOException e) {
			e.printStackTrace();
		}

		response.sendRedirect("ModifUserProfil");

	}

}
