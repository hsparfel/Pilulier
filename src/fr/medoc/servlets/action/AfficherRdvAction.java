package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.RdvDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Rdv;
import fr.medoc.entities.Utilisateur;
import fr.medoc.entities.Medecin;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherRdvAction")
public class AfficherRdvAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private RdvDAO rdvDao;
	private MedecinDAO medecinDAO;
	private UtilisateurDAO utilisateurDAO;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			rdvDao = daoFactory.getRdvDAO();
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
		int idRdv = (Integer) Integer.parseInt(request.getParameter("idRdv"));
		HttpSession session = request.getSession();
		Utilisateur unUtilisateur = null;
		Medecin unMedecin = null;
		
		String date = request.getParameter("date");
		String heure = request.getParameter("heure");
		String commentaire = request.getParameter("commentaire");
		String submitForm = request.getParameter("submit");

		try {
			unUtilisateur = utilisateurDAO.findByName((String) session.getAttribute("login"));
			
			if (submitForm != null) {
				if ("Valider".equals(submitForm)) {
					int idMedecin = (Integer) Integer.parseInt(request.getParameter("idMedecin"));
					unMedecin = medecinDAO.findByRef(idMedecin);
					Rdv nouveauRdv = new Rdv();
					nouveauRdv.setUtilisateur(unUtilisateur);
					nouveauRdv.setMedecin(unMedecin);
					nouveauRdv.setDate(date);
					nouveauRdv.setHeure(heure);
					nouveauRdv.setCommentaire(commentaire);
					rdvDao.modifierRdv(nouveauRdv, idRdv);
				}
				if ("Supprimer".equals(submitForm)) {
					rdvDao.supprimerRdv(idRdv);
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect("EnregistrerRdv");
	}
}
