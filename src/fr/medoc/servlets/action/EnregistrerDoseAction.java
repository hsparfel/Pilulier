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


@WebServlet("/EnregistrerDoseAction")
public class EnregistrerDoseAction extends HttpServlet {
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomDose = request.getParameter("nomDose");
	
		Dose nouveauDose = new Dose(nomDose);
		
		try {
			doseDao.ajouterDose(nouveauDose);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("EnregistrerPrescription");
		
	}

}
