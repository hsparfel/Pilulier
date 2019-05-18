package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherUtilisateurAction")
public class AfficherUtilisateurAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private UtilisateurDAO utilisateurDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			utilisateurDao = daoFactory.getUtilisateurDAO();
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
		int idUtilisateur = (Integer) Integer.parseInt(request.getParameter("idUtilisateur"));
		String nomUtilisateur = request.getParameter("nomUtilisateur");
	
		Utilisateur nouveauUtilisateur = new Utilisateur(nomUtilisateur);
		String submitForm = request.getParameter("submit");
		
		try {
			if (submitForm != null) {
				if ("Valider".equals(submitForm)) {
										utilisateurDao.modifierUtilisateur(nouveauUtilisateur, idUtilisateur);
				}
				if ("Supprimer".equals(submitForm)) {
										utilisateurDao.supprimerUtilisateur(idUtilisateur);
				}
			}
		} catch (DAOException e1) {
						e1.printStackTrace();
		}
		
		
		if (nouveauUtilisateur.getNom() != null) {
			try {
				response.sendRedirect("AfficherUtilisateur?id=" + utilisateurDao.findByName(nouveauUtilisateur.getNom()).getId());
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("EnregistrerUtilisateur");
		}
	}
}
