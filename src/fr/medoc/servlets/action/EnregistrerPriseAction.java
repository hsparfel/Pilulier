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
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.PrescriptionDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Prise;
import fr.medoc.entities.Utilisateur;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Prescription;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/EnregistrerPriseAction")
public class EnregistrerPriseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private PriseDAO priseDao;
	private PrescriptionDAO prescriptionDAO;
	
	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			priseDao = daoFactory.getPriseDAO();
			prescriptionDAO = daoFactory.getPrescriptionDAO();
			
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
		
		int idPrise = (Integer) Integer.parseInt(request.getParameter("idPrise"));
		

		String date = request.getParameter("date");
		String heure = request.getParameter("heure");
		
		
		
		Prescription unePrescription = null;

		
		try {
			unePrescription = prescriptionDAO.findByRef(idPrise);
			
			
			Prise nouveauPrise = new Prise(unePrescription, date, heure);
			
			
			priseDao.ajouterPrise(nouveauPrise);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		response.sendRedirect("EnregistrerPrise");

	}

}
