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
import fr.medoc.dao.OrdoAnalyseDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.OrdonnanceDAO;
import fr.medoc.dao.OrdoExamenDAO;

import fr.medoc.dao.OrdoPrescriptionDAO;
import fr.medoc.dao.PriseDAO;
import fr.medoc.dao.ProfilDAO;
import fr.medoc.dao.RdvDAO;
import fr.medoc.entities.OrdoAnalyse;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Ordonnance;
import fr.medoc.entities.OrdoExamen;
import fr.medoc.entities.OrdoPrescription;
import fr.medoc.entities.Prise;
import fr.medoc.entities.Profil;
import fr.medoc.entities.Rdv;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/ModifUserProfil")
public class ModifUserProfilServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String JSP_PAGE = "/WEB-INF/ModifUserProfil.jsp";
	private DAOFactory daoFactory;
	private OrdoPrescriptionDAO ordoPrescriptionDao;
	private OrdoAnalyseDAO ordoAnalyseDao;
	private OrdoExamenDAO ordoExamenDao;
	private UtilisateurDAO utilisateurDao;
	private PriseDAO priseDao;
	private MedecinDAO medecinDao;
	private RdvDAO rdvDao;
	private ProfilDAO profilDao;
	private OrdonnanceDAO ordonnanceDao;
	private LocalDate dateDuJour = null;

	@Override
	public void init() throws ServletException {
		try {
			dateDuJour = LocalDate.now();
			daoFactory = DAOFactory.getInstance();
			ordoPrescriptionDao = daoFactory.getOrdoPrescriptionDAO();
			ordoAnalyseDao = daoFactory.getOrdoAnalyseDAO();
			ordoExamenDao = daoFactory.getOrdoExamenDAO();
			utilisateurDao = daoFactory.getUtilisateurDAO();
			priseDao = daoFactory.getPriseDAO();
			medecinDao = daoFactory.getMedecinDAO();
			rdvDao = daoFactory.getRdvDAO();
			profilDao = daoFactory.getProfilDAO();
			ordonnanceDao = daoFactory.getOrdonnanceDAO();
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
			ArrayList<OrdoPrescription> listeOrdoPrescriptions = null;
			ArrayList<OrdoExamen> listeOrdoExamens = null;
			ArrayList<OrdoAnalyse> listeOrdoAnalyses = null;
			ArrayList<Prise> listePrises = null;
			ArrayList<Medecin> listeMedecins = null;
			ArrayList<Rdv> listeRdvs = null;
			Utilisateur unUtilisateur = null;
			ArrayList<Profil> listeProfils = null;
			ArrayList<Ordonnance> listeOrdonnances = null;
			Profil profilRecent=null;
			try {
				unUtilisateur = utilisateurDao.findByName((String) session.getAttribute("login"));
				listeProfils = (ArrayList<Profil>) profilDao.findAllByUser(unUtilisateur.getId());
				listeOrdoAnalyses = (ArrayList<OrdoAnalyse>) ordoAnalyseDao.findAllByUser(unUtilisateur.getId());
				listeMedecins = (ArrayList<Medecin>) medecinDao.findAllByUser(unUtilisateur.getId());
				listeRdvs = (ArrayList<Rdv>) rdvDao.findAllByUser(unUtilisateur.getId());
				listeOrdoPrescriptions = (ArrayList<OrdoPrescription>) ordoPrescriptionDao.findAllByUser(unUtilisateur.getId());
				listeOrdoExamens = (ArrayList<OrdoExamen>) ordoExamenDao.findAllByUser(unUtilisateur.getId());
				listePrises = (ArrayList<Prise>) priseDao.findAllByUser(unUtilisateur.getId());
				listeOrdonnances = (ArrayList<Ordonnance>) ordonnanceDao.findAllByUser(unUtilisateur.getId());
				if (listeProfils.size()>0) {
					profilRecent=listeProfils.get(0);
					for (Profil profilTemp : listeProfils) {
						int	profilRecentAnnee=(Integer) Integer.parseInt(profilRecent.getDate().substring(6,10));
						int	profilRecentMois=(Integer) Integer.parseInt(profilRecent.getDate().substring(3,5));
						int	profilRecentJour=(Integer) Integer.parseInt(profilRecent.getDate().substring(0,2));
						int	profilTempAnnee=(Integer) Integer.parseInt(profilTemp.getDate().substring(6,10));
						int	profilTempMois=(Integer) Integer.parseInt(profilTemp.getDate().substring(3,5));
						int	profilTempJour=(Integer) Integer.parseInt(profilTemp.getDate().substring(0,2));

						if (profilRecentAnnee<profilTempAnnee) {
							profilRecent=profilTemp;
						} else if (profilRecentAnnee==profilTempAnnee){
							if (profilRecentMois<profilTempMois) {
								profilRecent=profilTemp;
							} else if(profilRecentMois==profilTempMois){
								if (profilRecentJour<profilTempJour) {
									profilRecent=profilTemp;
								}
							}
						}
					}
				}
			} catch (DAOException e) {
				e.printStackTrace();
			}
			//extraire profil le plus recent de cette liste et l'envoyer 

			//faire pareil pour examen et rdv puis modifier pour prescription
			//trier analyse
			ArrayList<OrdoAnalyse> listeOrdoAnalysesTries = new ArrayList<OrdoAnalyse>();
			DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd");
			int formattedDay = Integer.parseInt(dateDuJour.format(formatterDay));
			DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
			int formattedMonth = Integer.parseInt(dateDuJour.format(formatterMonth));
			DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yyyy");
			int formattedYear = Integer.parseInt(dateDuJour.format(formatterYear));

			for (OrdoAnalyse ordoAnalyse : listeOrdoAnalyses) { 
				int analyseJour = Integer.parseInt(ordoAnalyse.getDate().substring(0, 2));
				int analyseMois = Integer.parseInt(ordoAnalyse.getDate().substring(3, 5));
				int analyseAnnee = Integer.parseInt(ordoAnalyse.getDate().substring(6, 10));

				if (analyseAnnee>formattedYear) {

					listeOrdoAnalysesTries.add(ordoAnalyse);
				} else if (analyseAnnee==formattedYear) {
					if (analyseMois>formattedMonth) {

						listeOrdoAnalysesTries.add(ordoAnalyse);
					} else if (analyseMois==formattedMonth) {
						if (analyseJour>=formattedDay) {

							listeOrdoAnalysesTries.add(ordoAnalyse);
						}
					}
				}
			} 
			//
			//trier examen
			ArrayList<OrdoExamen> listeOrdoExamensTries = new ArrayList<OrdoExamen>();
			for (OrdoExamen ordoExamen : listeOrdoExamens) { 
				int examenJour = Integer.parseInt(ordoExamen.getDate().substring(0, 2));
				int examenMois = Integer.parseInt(ordoExamen.getDate().substring(3, 5));
				int examenAnnee = Integer.parseInt(ordoExamen.getDate().substring(6, 10));
				if (examenAnnee>formattedYear) {
					listeOrdoExamensTries.add(ordoExamen);
				} else if (examenAnnee==formattedYear) {
					if (examenMois>formattedMonth) {
						listeOrdoExamensTries.add(ordoExamen);
					} else if (examenMois==formattedMonth) {
						if (examenJour>=formattedDay) {
							listeOrdoExamensTries.add(ordoExamen);
						}
					}
				}
			} 
			//
			//trier rdv
			ArrayList<Rdv> listeRdvsTries = new ArrayList<Rdv>();
			for (Rdv rdv : listeRdvs) { 
				int rdvJour = Integer.parseInt(rdv.getDate().substring(0, 2));
				int rdvMois = Integer.parseInt(rdv.getDate().substring(3, 5));
				int rdvAnnee = Integer.parseInt(rdv.getDate().substring(6, 10));
				if (rdvAnnee>formattedYear) {
					listeRdvsTries.add(rdv);
				} else if (rdvAnnee==formattedYear) {
					if (rdvMois>formattedMonth) {
						listeRdvsTries.add(rdv);
					} else if (rdvMois==formattedMonth) {
						if (rdvJour>=formattedDay) {
							listeRdvsTries.add(rdv);
						}
					}
				}
			} 
			//
			//trier ordonnance
			ArrayList<Ordonnance> listeOrdonnancesTries = new ArrayList<Ordonnance>();
			for (Ordonnance ordonnance : listeOrdonnances) { 
				int ordonnanceJour = Integer.parseInt(ordonnance.getDate().substring(0, 2));
				int ordonnanceMois = Integer.parseInt(ordonnance.getDate().substring(3, 5));
				int ordonnanceAnnee = Integer.parseInt(ordonnance.getDate().substring(6, 10));
				if (ordonnanceAnnee>formattedYear) {
					listeOrdonnancesTries.add(ordonnance);
				} else if (ordonnanceAnnee==formattedYear) {
					if (ordonnanceMois>formattedMonth) {
						listeOrdonnancesTries.add(ordonnance);
					} else if (ordonnanceMois==formattedMonth) {
						if (ordonnanceJour>=formattedDay) {
							listeOrdonnancesTries.add(ordonnance);
						}
					}
				}
			} 
			//
			//trier prescription
			ArrayList<OrdoPrescription> listeOrdoPrescriptionsTries = new ArrayList<OrdoPrescription>();
			for (OrdoPrescription prescription : listeOrdoPrescriptions) { 
				int prescriptionJour = Integer.parseInt(prescription.getDateFin().substring(0, 2));
				int prescriptionMois = Integer.parseInt(prescription.getDateFin().substring(3, 5));
				int prescriptionAnnee = Integer.parseInt(prescription.getDateFin().substring(6, 10));
				if (prescriptionAnnee>formattedYear) {
					listeOrdoPrescriptionsTries.add(prescription);
				} else if (prescriptionAnnee==formattedYear) {
					if (prescriptionMois>formattedMonth) {
						listeOrdoPrescriptionsTries.add(prescription);
					} else if (prescriptionMois==formattedMonth) {
						if (prescriptionJour>=formattedDay) {
							listeOrdoPrescriptionsTries.add(prescription);
						}
					}
				}
			} 
			//
			request.setAttribute("listeOrdonnances", listeOrdonnancesTries);
			request.setAttribute("listeOrdoAnalyses", listeOrdoAnalysesTries);
			request.setAttribute("listeOrdoPrescriptions", listeOrdoPrescriptionsTries);
			request.setAttribute("listeOrdoExamens", listeOrdoExamensTries);
			request.setAttribute("profil", profilRecent);
			request.setAttribute("listeMedecins", listeMedecins);
			request.setAttribute("listeRdvs", listeRdvsTries);
			request.setAttribute("login", (String) session.getAttribute("login"));
			request.setAttribute("utilisateur", unUtilisateur);

			request.setAttribute("listePrises", listePrises);
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} else {
			response.sendRedirect("Accueil");
		}
	}
}