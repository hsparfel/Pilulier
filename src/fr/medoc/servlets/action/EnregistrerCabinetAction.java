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


@WebServlet("/EnregistrerCabinetAction")
public class EnregistrerCabinetAction extends HttpServlet {
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomCabinet = request.getParameter("nomCabinet");
		String adresseCabinet = request.getParameter("adresseCabinet");
		String cpCabinet = request.getParameter("cpCabinet");
		String villeCabinet = request.getParameter("villeCabinet");
		
		Cabinet nouveauCabinet = new Cabinet(nomCabinet,adresseCabinet,cpCabinet,villeCabinet);
		
		try {
			cabinetDao.ajouterCabinet(nouveauCabinet);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("EnregistrerMedecin");
		
	}

}
