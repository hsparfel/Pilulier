package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.medoc.dao.FrequenceDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Frequence;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherFrequenceAction")
public class AfficherFrequenceAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private FrequenceDAO frequenceDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			frequenceDao = daoFactory.getFrequenceDAO();
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
		int idFrequence = (Integer) Integer.parseInt(request.getParameter("idFrequence"));
		String nomFrequence = request.getParameter("nomFrequence");
	
		Frequence nouveauFrequence = new Frequence(nomFrequence);
		String submitForm = request.getParameter("submit");
		
		try {
			if (submitForm != null) {
				if ("Valider".equals(submitForm)) {
										frequenceDao.modifierFrequence(nouveauFrequence, idFrequence);
				}
				if ("Supprimer".equals(submitForm)) {
										frequenceDao.supprimerFrequence(idFrequence);
				}
			}
		} catch (DAOException e1) {
						e1.printStackTrace();
		}
		
		
		if (nouveauFrequence.getNom() != null) {
			try {
				response.sendRedirect("AfficherFrequence?id=" + frequenceDao.findByName(nouveauFrequence.getNom()).getId());
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("EnregistrerFrequence");
		}
	}
}
