package fr.medoc.servlets.page;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.OrdonnanceDAO;
import fr.medoc.dao.OrdoPrescriptionDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.OrdoAnalyseDAO;
import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.OrdoExamenDAO;
import fr.medoc.entities.OrdoAnalyse;
import fr.medoc.entities.Cabinet;
import fr.medoc.entities.Dose;
import fr.medoc.entities.OrdoExamen;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Ordonnance;
import fr.medoc.entities.OrdoPrescription;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherOrdonnance")
public class AfficherOrdonnanceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String JSP_PAGE = "/WEB-INF/AfficherOrdonnance.jsp";
	private DAOFactory daoFactory;
	private OrdonnanceDAO ordonnanceDao;
	private UtilisateurDAO utilisateurDao;
	private MedecinDAO medecinDao;
	private MedicamentDAO medicamentDao;
	private DoseDAO doseDao;
	private OrdoAnalyseDAO ordoAnalyseDao;
	private OrdoExamenDAO ordoExamenDao;
	private CabinetDAO cabinetDao;
	private OrdoPrescriptionDAO ordoPrescriptionDao;
	
	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			ordonnanceDao = daoFactory.getOrdonnanceDAO();
			utilisateurDao = daoFactory.getUtilisateurDAO();
			medecinDao = daoFactory.getMedecinDAO();
			medicamentDao = daoFactory.getMedicamentDAO();
			doseDao = daoFactory.getDoseDAO();
			ordoAnalyseDao = daoFactory.getOrdoAnalyseDAO();
			ordoExamenDao = daoFactory.getOrdoExamenDAO();
			cabinetDao = daoFactory.getCabinetDAO();
			ordoPrescriptionDao = daoFactory.getOrdoPrescriptionDAO();
			
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
		int idOrdonnance = (Integer) Integer.parseInt(request.getParameter("id"));
		if (session.getAttribute("login") != null) {
			Ordonnance myOrdonnance = null;
			ArrayList<Ordonnance> listeOrdonnances = null;
			Utilisateur unUtilisateur = null;
			ArrayList<Medecin> listeMedecins = null;
			
			ArrayList<Medicament> listeMedicaments = null;
			ArrayList<Dose> listeDoses = null;
			ArrayList<OrdoAnalyse> listeAnalyses = null;
			ArrayList<OrdoAnalyse> listeAnalysesOrdonnance = null;
			ArrayList<OrdoExamen> listeExamens = null;
			ArrayList<OrdoExamen> listeExamensOrdonnance = null;
			ArrayList<Cabinet> listeCabinets = null;
			//ArrayList<OrdoPrescription> listePrescriptions = null;
			ArrayList<OrdoPrescription> listePrescriptionsOrdonnance = null;
			
			try {
				myOrdonnance = ordonnanceDao.findByRef(idOrdonnance);
				unUtilisateur = utilisateurDao.findByName((String) session.getAttribute("login"));
				listeOrdonnances = (ArrayList<Ordonnance>) ordonnanceDao.findAllByUser(unUtilisateur.getId());
				listeMedecins = (ArrayList<Medecin>) medecinDao.findAllByUser(unUtilisateur.getId());
				listeMedicaments = (ArrayList<Medicament>) medicamentDao.findAll();
				listeDoses = (ArrayList<Dose>) doseDao.findAll();
				listeAnalyses = (ArrayList<OrdoAnalyse>) ordoAnalyseDao.findAll();
				listeExamens = (ArrayList<OrdoExamen>) ordoExamenDao.findAll();
				//listePrescriptions = (ArrayList<OrdoPrescription>) prescriptionDao.findAllByUser(unUtilisateur.getId());
				listeCabinets = (ArrayList<Cabinet>) cabinetDao.findAll();
				listePrescriptionsOrdonnance = (ArrayList<OrdoPrescription>) ordoPrescriptionDao.findByOrdonnance(myOrdonnance.getId());
				listeExamensOrdonnance = (ArrayList<OrdoExamen>) ordoExamenDao.findByOrdonnance(myOrdonnance.getId());
				listeAnalysesOrdonnance = (ArrayList<OrdoAnalyse>) ordoAnalyseDao.findByOrdonnance(myOrdonnance.getId());
					System.out.println(listeExamensOrdonnance);			
			} catch (DAOException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeOrdonnances", listeOrdonnances);
			request.setAttribute("listeMedecins", listeMedecins);
			request.setAttribute("myOrdonnance", myOrdonnance);
			
			request.setAttribute("listeMedecins", listeMedecins);
			request.setAttribute("listeMedicaments", listeMedicaments);
			request.setAttribute("listeDoses", listeDoses);
			request.setAttribute("listeAnalyses", listeAnalyses);
			request.setAttribute("listeExamens", listeExamens);
			request.setAttribute("listeCabinets", listeCabinets);
			//request.setAttribute("listePrescriptions", listePrescriptions);
			//ajouter les infos des prescriptions
			int k=1;
			for (OrdoPrescription prescription : listePrescriptionsOrdonnance) {
				System.out.println(prescription);
				//System.out.println(prescription.getNom().getName());
				request.setAttribute("myPrescription"+k, prescription);
				k++;
			}			
			//ajouter les infos des analyses
			int j=1;
			for (OrdoAnalyse ordoAnalyse : listeAnalysesOrdonnance) {
				System.out.println(ordoAnalyse);
				System.out.println(ordoAnalyse.getNom().getName());
				request.setAttribute("myAnalyse"+j, ordoAnalyse);
				j++;
			}		
			//ajouter les infos des examens
			int i=1;
			for (OrdoExamen ordoExamen : listeExamensOrdonnance) {
				System.out.println(ordoExamen);
				System.out.println(ordoExamen.getNom().getName());
				request.setAttribute("myExamen"+i, ordoExamen);
				i++;
			}
			
			
			
			
			
			
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} else {
			response.sendRedirect("Accueil");
		}
	}
}
