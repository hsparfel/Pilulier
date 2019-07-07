package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.ProfilDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.ProfilDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Profil;
import fr.medoc.entities.Utilisateur;
import fr.medoc.entities.Medecin;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/ModifierProfilAction")
public class ModifierProfilAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private ProfilDAO profilDao;
	private UtilisateurDAO utilisateurDAO;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			profilDao = daoFactory.getProfilDAO();

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
		float poids = (Float) Float.parseFloat(request.getParameter("poids"));
		int taille = (Integer) Integer.parseInt(request.getParameter("taille"));
		float imc = (Float) Float.parseFloat(request.getParameter("imc"));
		String date = request.getParameter("date");
		String commentaire = request.getParameter("imcComm");
		String sexe = request.getParameter("sexe");
		String dateDeNaissance = request.getParameter("dateDeNaissance");
		try {
			unUtilisateur = utilisateurDAO.findByName((String) session.getAttribute("login"));
			Profil nouveauProfil = new Profil();
			nouveauProfil.setUtilisateur(unUtilisateur);
			nouveauProfil.setPoids(poids);;
			nouveauProfil.setTaille(taille);
			nouveauProfil.setImc(imc);
			nouveauProfil.setDate(date);
			nouveauProfil.setCommentaire(commentaire);
			unUtilisateur.setDateDeNaissance(dateDeNaissance);
			unUtilisateur.setSexe(sexe);
			profilDao.ajouterProfil(nouveauProfil);
			utilisateurDAO.modifierUtilisateur(unUtilisateur, unUtilisateur.getId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		response.sendRedirect("ModifUserProfil");
	}
}
