package fr.medoc.servlets.page;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.PrescriptionDAO;
import fr.medoc.dao.PriseDAO;
import fr.medoc.entities.Dose;
import fr.medoc.entities.Frequence;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Prescription;
import fr.medoc.entities.Prise;
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

			ArrayList<Prescription> listePrescriptionsTries = null;
			ArrayList<String> listePrescriptionsTriesString = new ArrayList<String>();
			ArrayList<Medicament> listeMedicamentsTries = null;
			ArrayList<Dose> listeDoses = null;
			ArrayList<Frequence> listeFrequences = null;
			ArrayList<Prise> listePrises = null;
			ArrayList<String> listePrisesString = new ArrayList<String>();
			Utilisateur unUtilisateur = null;

			try {
				listeFrequences = (ArrayList<Frequence>) frequenceDao.findAll();
				listeDoses = (ArrayList<Dose>) doseDao.findAll();

				unUtilisateur = utilisateurDao.findByName((String) session.getAttribute("login"));
				System.out.println(unUtilisateur.getId());
				
				listePrescriptionsTries = (ArrayList<Prescription>) prescriptionDao
						.findAllByUser(unUtilisateur.getId());
				for (int i = 0; i < listePrescriptionsTries.size(); i++) {
					 Medicament unMedoc = medicamentDao.findByRef(listePrescriptionsTries.get(i).getIdMedicament());
					 int dose=listePrescriptionsTries.get(i).getNbDose();
					 Dose uneDose = doseDao.findByRef(listePrescriptionsTries.get(i).getIdDose());
					 int frequence=listePrescriptionsTries.get(i).getNbFrequence();
					 Frequence uneFrequence =frequenceDao.findByRef(listePrescriptionsTries.get(i).getIdFrequence());
					 String prescriptionString = unMedoc.getNom()+" - "+dose+" "+uneDose.getNom()+ " "+frequence+ " fois par "+uneFrequence.getNom();
					 System.out.println(prescriptionString);
					listePrescriptionsTriesString.add(prescriptionString);
				}
				
				// System.out.println(listePrescriptionsTriesString);
				listeMedicamentsTries = (ArrayList<Medicament>) medicamentDao
						.findAllExcludedByUser((String) session.getAttribute("login"));
				
				
				listePrises = (ArrayList<Prise>) priseDao.findAllLastByUser(unUtilisateur.getId());
				for (int i = 0; i < listePrises.size(); i++) {
					 Medicament unMedoc = medicamentDao.findByRef(listePrises.get(i).getIdMedicament());
					 String date =listePrises.get(i).getDatePrise();
					 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					 Date dateFormatDate=null;;
					try {
						dateFormatDate = dateFormat.parse(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 SimpleDateFormat formater = new SimpleDateFormat("EEEE, d MMM yyyy");
					 
					 
					 
					 
					 
					 
					 String priseString = unMedoc.getNom()+" - "+formater.format(dateFormatDate);
					 System.out.println(priseString);
					 listePrisesString.add(priseString);
				}
				
				
				
				
			} catch (DAOException e) {
				e.printStackTrace();
			}

			request.setAttribute("listeFrequences", listeFrequences);
			request.setAttribute("listeDoses", listeDoses);
			request.setAttribute("listePrescriptionsTries", listePrescriptionsTriesString);
			request.setAttribute("listeMedicamentsTries", listeMedicamentsTries);
			request.setAttribute("listePrises", listePrisesString);

			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);

		} else {
			response.sendRedirect("Accueil");
		}
	}

}