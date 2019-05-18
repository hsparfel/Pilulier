package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Cabinet;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherCabinetAction")
public class AfficherCabinetAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOFactory daoFactory;
	private CabinetDAO cabinetDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			cabinetDao = daoFactory.getCabinetDAO();
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
		int idCabinet = (Integer) Integer.parseInt(request.getParameter("idCabinet"));
		
		String nomCabinet = request.getParameter("nomCabinet");
		
		String adresseCabinet = request.getParameter("adresseCabinet");
		
		String cpCabinet = request.getParameter("cpCabinet");
		
		String villeCabinet = request.getParameter("villeCabinet");
		
		Cabinet nouveauCabinet = new Cabinet(nomCabinet, adresseCabinet, cpCabinet, villeCabinet);
		String submitForm = request.getParameter("submit");

		try {
			if (submitForm != null) {
				if ("Valider".equals(submitForm)) {
					cabinetDao.modifierCabinet(nouveauCabinet, idCabinet);
				}
				if ("Supprimer".equals(submitForm)) {
					cabinetDao.supprimerCabinet(idCabinet);
				}
			}
		} catch (DAOException e1) {
			e1.printStackTrace();
		}

		if (nouveauCabinet.getNom() != null) {
			try {
				response.sendRedirect("AfficherCabinet?id=" + cabinetDao.findByName(nouveauCabinet.getNom()).getId());
			} catch (DAOException e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("EnregistrerCabinet");
		}
	}
}
