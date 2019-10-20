package fr.medoc.servlets.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.medoc.dao.OrdoAnalyseDAO;
import fr.medoc.dao.AnalyseDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Analyse;
import fr.medoc.entities.OrdoAnalyse;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;


@WebServlet("/EnregistrerAnalyseAction")
public class EnregistrerAnalyseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DAOFactory daoFactory;
	private AnalyseDAO analyseDao;

	@Override 
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			analyseDao = daoFactory.getAnalyseDAO();
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
		String nomAnalyse = request.getParameter("nomAnalyse");
	
		Analyse nouveauAnalyse = new Analyse(nomAnalyse);
		
		try {
			analyseDao.ajouterAnalyse(nouveauAnalyse);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("EnregistrerAnalyse");
		
	}

}
