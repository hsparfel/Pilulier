package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.medoc.dao.SpecialiteDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Specialite;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherSpecialiteAction")
public class AfficherSpecialiteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private SpecialiteDAO specialiteDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			specialiteDao = daoFactory.getSpecialiteDAO();
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
		int idSpecialite = (Integer) Integer.parseInt(request.getParameter("idSpecialite"));
		String nomSpecialite = request.getParameter("nomSpecialite");
	
		Specialite nouveauSpecialite = new Specialite(nomSpecialite);
		String submitForm = request.getParameter("submit");
		
		try {
			if (submitForm != null) {
				if ("Valider".equals(submitForm)) {
										specialiteDao.modifierSpecialite(nouveauSpecialite, idSpecialite);
				}
				if ("Supprimer".equals(submitForm)) {
										specialiteDao.supprimerSpecialite(idSpecialite);
				}
			}
		} catch (DAOException e1) {
						e1.printStackTrace();
		}
		
		
		if (nouveauSpecialite.getNom() != null) {
			try {
				response.sendRedirect("AfficherSpecialite?id=" + specialiteDao.findByName(nouveauSpecialite.getNom()).getId());
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("EnregistrerSpecialite");
		}
	}
}
