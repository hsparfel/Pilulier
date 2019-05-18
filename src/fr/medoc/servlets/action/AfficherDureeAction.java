package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.medoc.dao.DureeDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Duree;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherDureeAction")
public class AfficherDureeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private DureeDAO dureeDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			dureeDao = daoFactory.getDureeDAO();
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
		int idDuree = (Integer) Integer.parseInt(request.getParameter("idDuree"));
		String nomDuree = request.getParameter("nomDuree");
	
		Duree nouveauDuree = new Duree(nomDuree);
		String submitForm = request.getParameter("submit");
		
		try {
			if (submitForm != null) {
				if ("Valider".equals(submitForm)) {
										dureeDao.modifierDuree(nouveauDuree, idDuree);
				}
				if ("Supprimer".equals(submitForm)) {
										dureeDao.supprimerDuree(idDuree);
				}
			}
		} catch (DAOException e1) {
						e1.printStackTrace();
		}
		
		
		if (nouveauDuree.getNom() != null) {
			try {
				response.sendRedirect("AfficherDuree?id=" + dureeDao.findByName(nouveauDuree.getNom()).getId());
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("EnregistrerDuree");
		}
	}
}
