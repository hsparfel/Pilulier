package fr.medoc.servlets.page;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.AnalyseDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.ExamenDAO;

import fr.medoc.dao.Prescription2DAO;
import fr.medoc.dao.PriseDAO;
import fr.medoc.dao.RdvDAO;
import fr.medoc.entities.Analyse;
import fr.medoc.entities.Dose;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Examen;
import fr.medoc.entities.Prescription2;
import fr.medoc.entities.Prise;
import fr.medoc.entities.Rdv;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/ModifUserProfil")
public class ModifUserProfilServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String JSP_PAGE = "/WEB-INF/ModifUserProfil.jsp";
	private DAOFactory daoFactory;
	private Prescription2DAO prescriptionDao;
	private AnalyseDAO analyseDao;
	private ExamenDAO examenDao;
	private UtilisateurDAO utilisateurDao;
	private PriseDAO priseDao;
	private MedecinDAO medecinDao;
	private RdvDAO rdvDao;
//	private LocalDate dateDuJour = null;

	@Override
	public void init() throws ServletException {
		try {
			//dateDuJour = LocalDate.now();
			daoFactory = DAOFactory.getInstance();
			prescriptionDao = daoFactory.getPrescription2DAO();
		analyseDao = daoFactory.getAnalyseDAO();
			examenDao = daoFactory.getExamenDAO();
			utilisateurDao = daoFactory.getUtilisateurDAO();
			priseDao = daoFactory.getPriseDAO();
			medecinDao = daoFactory.getMedecinDAO();
			rdvDao = daoFactory.getRdvDAO();
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
			ArrayList<Prescription2> listePrescriptions = null;
			ArrayList<Examen> listeExamens = null;
			ArrayList<Analyse> listeAnalyses = null;
			ArrayList<Prise> listePrises = null;
			ArrayList<Medecin> listeMedecins = null;
			ArrayList<Rdv> listeRdvs = null;
			Utilisateur unUtilisateur = null;
			try {
				unUtilisateur = utilisateurDao.findByName((String) session.getAttribute("login"));
				listeAnalyses = (ArrayList<Analyse>) analyseDao.findAllByUser(unUtilisateur.getId());
				listeMedecins = (ArrayList<Medecin>) medecinDao.findAllByUser(unUtilisateur.getId());
				listeRdvs = (ArrayList<Rdv>) rdvDao.findAllByUser(unUtilisateur.getId());
				listePrescriptions = (ArrayList<Prescription2>) prescriptionDao.findAllByUser(unUtilisateur.getId());
				listeExamens = (ArrayList<Examen>) examenDao.findAllByUser(unUtilisateur.getId());
				listePrises = (ArrayList<Prise>) priseDao.findAllByUser(unUtilisateur.getId());
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("listeAnalyses", listeAnalyses);
			request.setAttribute("listePrescriptions", listePrescriptions);
			request.setAttribute("listeExamens", listeExamens);
		
			request.setAttribute("listeMedecins", listeMedecins);
			request.setAttribute("listeRdvs", listeRdvs);
			request.setAttribute("login", (String) session.getAttribute("login"));
			
			
			request.setAttribute("listePrises", listePrises);
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} else {
			response.sendRedirect("Accueil");
		}
	}
}