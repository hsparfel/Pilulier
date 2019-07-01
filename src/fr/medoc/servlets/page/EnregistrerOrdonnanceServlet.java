package fr.medoc.servlets.page;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.Prescription2DAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.entities.Cabinet;
import fr.medoc.entities.Dose;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Medicament;

import fr.medoc.entities.Utilisateur;
import fr.medoc.enumeration.EnumAnalyse;
import fr.medoc.enumeration.EnumDuree;
import fr.medoc.enumeration.EnumExamen;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/EnregistrerOrdonnance")
public class EnregistrerOrdonnanceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String JSP_PAGE = "/WEB-INF/EnregistrerOrdonnance.jsp";
	private DAOFactory daoFactory;
	private Prescription2DAO prescriptionDao;
	private UtilisateurDAO utilisateurDao;
	private DoseDAO doseDao;
	private MedecinDAO medecinDao;
	private MedicamentDAO medicamentDao;
	private CabinetDAO cabinetDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			//prescriptionDao = daoFactory.getPrescriptionDAO();
			utilisateurDao = daoFactory.getUtilisateurDAO();
			doseDao = daoFactory.getDoseDAO();
			medecinDao = daoFactory.getMedecinDAO();
			medicamentDao = daoFactory.getMedicamentDAO();
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("login") != null) {
			//ArrayList<Prescription> listePrescriptions = null;
			Utilisateur unUtilisateur = null;
			ArrayList<Dose> listeDoses = null;
			//ArrayList<Duree> listeDurees = null;
			//ArrayList<Frequence> listeFrequences = null;
			ArrayList<Medecin> listeMedecins = null;
			ArrayList<Medicament> listeMedicaments = null;
			ArrayList<Cabinet> listeCabinets = null;
			try {
				unUtilisateur = utilisateurDao.findByName((String) session.getAttribute("login"));
				//listePrescriptions = (ArrayList<Prescription>) prescriptionDao.findAllByUser(unUtilisateur.getId());
				listeDoses = (ArrayList<Dose>) doseDao.findAll();
				//listeDurees = (ArrayList<Duree>) dureeDao.findAll();
				//listeFrequences = (ArrayList<Frequence>) frequenceDao.findAll();
				listeMedecins = (ArrayList<Medecin>) medecinDao.findAllByUser(unUtilisateur.getId());
				listeMedicaments = (ArrayList<Medicament>) medicamentDao.findAll();
				listeCabinets = (ArrayList<Cabinet>) cabinetDao.findAll();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
			EnumAnalyse[] listeAnalyses =  EnumAnalyse.values();
			EnumDuree[] listeDurees =  EnumDuree.values();
			EnumExamen[] listeExamens =  EnumExamen.values();
			
			request.setAttribute("listeExamens", listeExamens);
			request.setAttribute("listeAnalyses", listeAnalyses);
			//request.setAttribute("listePrescriptions", listePrescriptions);
			request.setAttribute("listeDoses", listeDoses);
			request.setAttribute("listeDurees", listeDurees);
			//request.setAttribute("listeFrequences", listeFrequences);
			request.setAttribute("listeMedecins", listeMedecins);
			request.setAttribute("listeMedicaments", listeMedicaments);
			request.setAttribute("listeCabinets", listeCabinets);
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} else {
			response.sendRedirect("Accueil");
		}
	}
}
