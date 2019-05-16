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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("id à modif: "+request.getParameter("idCabinet"));
		int idCabinet = (Integer) Integer.parseInt(request.getParameter("idCabinet"));
		
		String nomCabinet = request.getParameter("nomCabinet");
		System.out.println(nomCabinet);
		String adresseCabinet = request.getParameter("adresseCabinet");
		String cpCabinet = request.getParameter("cpCabinet");
		String villeCabinet = request.getParameter("villeCabinet");
		
		Cabinet nouveauCabinet = new Cabinet(nomCabinet,adresseCabinet,cpCabinet,villeCabinet);
		
		//cabinetDao.ajouterCabinet(nouveauCabinet);
		try {
			cabinetDao.modifierCabinet(nouveauCabinet,idCabinet);
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(nouveauCabinet.getNom());
		try {
			System.out.println(cabinetDao.findByName(nouveauCabinet.getNom()));
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			response.sendRedirect("AfficherCabinet?id="+cabinetDao.findByName(nouveauCabinet.getNom()).getId());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

}
