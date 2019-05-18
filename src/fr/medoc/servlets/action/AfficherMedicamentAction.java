package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Medicament;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherMedicamentAction")
public class AfficherMedicamentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private MedicamentDAO medicamentDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			medicamentDao = daoFactory.getMedicamentDAO();
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
		int idMedicament = (Integer) Integer.parseInt(request.getParameter("idMedicament"));
		String nomMedicament = request.getParameter("nomMedicament");
	
		Medicament nouveauMedicament = new Medicament(nomMedicament);
		String submitForm = request.getParameter("submit");
		
		try {
			if (submitForm != null) {
				if ("Valider".equals(submitForm)) {
										medicamentDao.modifierMedicament(nouveauMedicament, idMedicament);
				}
				if ("Supprimer".equals(submitForm)) {
										medicamentDao.supprimerMedicament(idMedicament);
				}
			}
		} catch (DAOException e1) {
						e1.printStackTrace();
		}
		
		
		if (nouveauMedicament.getNom() != null) {
			try {
				response.sendRedirect("AfficherMedicament?id=" + medicamentDao.findByName(nouveauMedicament.getNom()).getId());
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("EnregistrerMedicament");
		}
	}
}
