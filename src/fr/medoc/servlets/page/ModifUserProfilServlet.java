package fr.medoc.servlets.page;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.FrequenceDAO;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.PrescriptionDAO;
import fr.medoc.dao.PriseDAO;
import fr.medoc.dao.RdvDAO;
import fr.medoc.entities.Dose;
import fr.medoc.entities.Frequence;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Prescription;
import fr.medoc.entities.Prise;
import fr.medoc.entities.Rdv;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/ModifUserProfil")
public class ModifUserProfilServlet extends HttpServlet {

	private final String JSP_PAGE = "/WEB-INF/ModifUserProfil.jsp";
	private DAOFactory daoFactory;
	private PrescriptionDAO prescriptionDao;
	private DoseDAO doseDao;
	private FrequenceDAO frequenceDao;
	private MedicamentDAO medicamentDao;
	private UtilisateurDAO utilisateurDao;
	private PriseDAO priseDao;
	private MedecinDAO medecinDao;
	private RdvDAO rdvDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			prescriptionDao = daoFactory.getPrescriptionDAO();
			doseDao = daoFactory.getDoseDAO();
			frequenceDao = daoFactory.getFrequenceDAO();
			medicamentDao = daoFactory.getMedicamentDAO();
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

			ArrayList<Prescription> listePrescriptions = null;
			ArrayList<Medicament> listeMedicaments = null;
			ArrayList<Dose> listeDoses = null;
			ArrayList<Frequence> listeFrequences = null;
			ArrayList<Prise> listePrises = null;
			ArrayList<Medecin> listeMedecins = null;
			ArrayList<Rdv> listeRdvs = null;
			Utilisateur unUtilisateur = null;

			try {
				unUtilisateur = utilisateurDao.findByName((String) session.getAttribute("login"));
				listeFrequences = (ArrayList<Frequence>) frequenceDao.findAll();
				listeDoses = (ArrayList<Dose>) doseDao.findAll();
				listeMedecins = (ArrayList<Medecin>) medecinDao.findAllByUser(unUtilisateur.getId());
				listeRdvs = (ArrayList<Rdv>) rdvDao.findAllByUser(unUtilisateur.getId());
				
				//System.out.println(unUtilisateur.getId());

				listePrescriptions = (ArrayList<Prescription>) prescriptionDao
						.findAllByUser(unUtilisateur.getId());
				

				// System.out.println(listePrescriptionsTriesString);
				listeMedicaments = (ArrayList<Medicament>) medicamentDao
						.findAllExcludedByUser((String) session.getAttribute("login"));

				listePrises = (ArrayList<Prise>) priseDao.findAllLastByUser(unUtilisateur.getId());
				

			} catch (DAOException e) {
				e.printStackTrace();
			}

			request.setAttribute("listeFrequences", listeFrequences);
			request.setAttribute("listeDoses", listeDoses);
			request.setAttribute("listePrescriptions", listePrescriptions);
			request.setAttribute("listeMedicaments", listeMedicaments);
			request.setAttribute("listePrises", listePrises);
			request.setAttribute("listeMedecins", listeMedecins);
			request.setAttribute("listeRdvs", listeRdvs);
			
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);

		} else {
			response.sendRedirect("Accueil");
		}
	}

}