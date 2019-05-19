package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.SpecialiteDAO;
import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Cabinet;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Specialite;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;


@WebServlet("/EnregistrerMedecinAction")
public class EnregistrerMedecinAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DAOFactory daoFactory;
	private MedecinDAO medecinDao;
	private CabinetDAO cabinetDAO;
	private SpecialiteDAO specialiteDAO;

	@Override 
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			medecinDao = daoFactory.getMedecinDAO();
			cabinetDAO= daoFactory.getCabinetDAO();
			specialiteDAO= daoFactory.getSpecialiteDAO();
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
		String nomMedecin = request.getParameter("nomMedecin");
		int idCabinet = (Integer) Integer.parseInt(request.getParameter("idCabinet"));
		int idSpecialite = (Integer) Integer.parseInt(request.getParameter("idSpecialite"));
		String nomTelephone = request.getParameter("nomTelephone");
		String nomEmail = request.getParameter("nomEmail");
		
		Cabinet unCabinet = null;
		Specialite uneSpecialite = null;
		
		try {
			unCabinet = cabinetDAO.findByRef(idCabinet);
			uneSpecialite = specialiteDAO.findByRef(idSpecialite);
			Medecin nouveauMedecin = new Medecin(nomMedecin,uneSpecialite,unCabinet,nomTelephone,nomEmail);
			medecinDao.ajouterMedecin(nouveauMedecin);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
		
		response.sendRedirect("EnregistrerMedecin");
		
	}

}
