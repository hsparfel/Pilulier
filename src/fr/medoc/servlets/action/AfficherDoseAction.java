package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Dose;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherDoseAction")
public class AfficherDoseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private DoseDAO doseDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			doseDao = daoFactory.getDoseDAO();
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
		int idDose = (Integer) Integer.parseInt(request.getParameter("idDose"));
		String nomDose = request.getParameter("nomDose");
	
		Dose nouveauDose = new Dose(nomDose);
		String submitForm = request.getParameter("submit");
		
		try {
			if (submitForm != null) {
				if ("Valider".equals(submitForm)) {
										doseDao.modifierDose(nouveauDose, idDose);
				}
				if ("Supprimer".equals(submitForm)) {
										doseDao.supprimerDose(idDose);
				}
			}
		} catch (DAOException e1) {
						e1.printStackTrace();
		}
		
		
		if (nouveauDose.getNom() != null) {
			try {
				response.sendRedirect("AfficherDose?id=" + doseDao.findByName(nouveauDose.getNom()).getId());
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("EnregistrerDose");
		}
	}
}
