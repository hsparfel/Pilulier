package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.medoc.dao.OrdoExamenDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.ExamenDAO;
import fr.medoc.entities.Examen;
import fr.medoc.entities.OrdoExamen;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;


@WebServlet("/EnregistrerExamenAction")
public class EnregistrerExamenAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DAOFactory daoFactory;
	private ExamenDAO examenDao;

	@Override 
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			examenDao = daoFactory.getExamenDAO();
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
		String nomExamen = request.getParameter("nomExamen");
	
		Examen nouveauExamen = new Examen(nomExamen);
		
		try {
			examenDao.ajouterExamen(nouveauExamen);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("EnregistrerExamen");
		
	}

}
