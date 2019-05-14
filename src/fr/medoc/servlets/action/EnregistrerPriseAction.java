package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.PriseDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.FrequenceDAO;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Dose;
import fr.medoc.entities.Prise;
import fr.medoc.entities.Utilisateur;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Frequence;
import fr.medoc.entities.Medecin;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/EnregistrerPriseAction")
public class EnregistrerPriseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private PriseDAO priseDao;
	private MedicamentDAO medicamentDAO;
	private UtilisateurDAO utilisateurDAO;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			priseDao = daoFactory.getPriseDAO();
			medicamentDAO = daoFactory.getMedicamentDAO();
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
		
		int idMedicament = (Integer) Integer.parseInt(request.getParameter("idMedicament"));
		

		String date = request.getParameter("date");
		String heure = request.getParameter("heure");
		
		
		
		Medicament unMedicament = null;

		
		try {
			unUtilisateur = utilisateurDAO.findByName((String) session.getAttribute("login"));
			
			unMedicament = medicamentDAO.findByRef(idMedicament);
			Prise nouveauPrise = new Prise(unUtilisateur,  unMedicament, date, heure);
			
			
			priseDao.ajouterPrise(nouveauPrise);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		response.sendRedirect("ModifUserProfil");

	}

}
