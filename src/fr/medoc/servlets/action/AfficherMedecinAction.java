package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.SpecialiteDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Cabinet;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Specialite;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherMedecinAction")
public class AfficherMedecinAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private MedecinDAO medecinDao;
	private SpecialiteDAO specialiteDAO;
	private CabinetDAO cabinetDAO;
	

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			medecinDao = daoFactory.getMedecinDAO();
			specialiteDAO = daoFactory.getSpecialiteDAO();
			cabinetDAO = daoFactory.getCabinetDAO();
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
		int idMedecin = (Integer) Integer.parseInt(request.getParameter("idMedecin"));
		Specialite uneSpecialite = null;
		Cabinet unCabinet = null;
		String nomMedecin = request.getParameter("nomMedecin");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String submitForm = request.getParameter("submit");
		try {
							if (submitForm != null) {
				if ("Valider".equals(submitForm)) {
					int idSpecialite = (Integer) Integer.parseInt(request.getParameter("idSpecialite"));
					int idCabinet = (Integer) Integer.parseInt(request.getParameter("idCabinet"));
					uneSpecialite = specialiteDAO.findByRef(idSpecialite);
					unCabinet = cabinetDAO.findByRef(idCabinet);
					
					Medecin nouveauMedecin = new Medecin(nomMedecin,uneSpecialite,unCabinet,telephone,email);
					medecinDao.modifierMedecin(nouveauMedecin, idMedecin);
				}
				if ("Supprimer".equals(submitForm)) {
					medecinDao.supprimerMedecin(idMedecin);
				}
			}

			
		} catch (DAOException e) {
			e.printStackTrace();
		}

		response.sendRedirect("EnregistrerMedecin");

	}

}
