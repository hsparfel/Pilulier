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

@WebServlet("/EnregistrerFrequenceAction")
public class EnregistrerFrequenceAction extends HttpServlet {
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomFrequence = request.getParameter("nomFrequence");
	
		Frequence nouveauFrequence = new Frequence(nomFrequence);
		
		try {
			frequenceDao.ajouterFrequence(nouveauFrequence);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("EnregistrerFrequence");
		
	}

}
