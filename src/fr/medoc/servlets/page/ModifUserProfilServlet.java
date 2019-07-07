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
import fr.medoc.dao.ProfilDAO;
import fr.medoc.dao.RdvDAO;
import fr.medoc.entities.Analyse;
import fr.medoc.entities.Dose;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Examen;
import fr.medoc.entities.Prescription2;
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
	private Prescription2DAO prescriptionDao;
	private AnalyseDAO analyseDao;
	private ExamenDAO examenDao;
	private UtilisateurDAO utilisateurDao;
	private PriseDAO priseDao;
	private MedecinDAO medecinDao;
	private RdvDAO rdvDao;
	private ProfilDAO profilDao;
	private LocalDate dateDuJour = null;

	@Override
	public void init() throws ServletException {
		try {
			dateDuJour = LocalDate.now();
			daoFactory = DAOFactory.getInstance();
			prescriptionDao = daoFactory.getPrescription2DAO();
			analyseDao = daoFactory.getAnalyseDAO();
			examenDao = daoFactory.getExamenDAO();
			utilisateurDao = daoFactory.getUtilisateurDAO();
			priseDao = daoFactory.getPriseDAO();
			medecinDao = daoFactory.getMedecinDAO();
			rdvDao = daoFactory.getRdvDAO();
			profilDao = daoFactory.getProfilDAO();
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
			ArrayList<Profil> listeProfils = null;
			Profil profilRecent=null;
			try {
				unUtilisateur = utilisateurDao.findByName((String) session.getAttribute("login"));
				listeProfils = (ArrayList<Profil>) profilDao.findAllByUser(unUtilisateur.getId());
				listeAnalyses = (ArrayList<Analyse>) analyseDao.findAllByUser(unUtilisateur.getId());
				listeMedecins = (ArrayList<Medecin>) medecinDao.findAllByUser(unUtilisateur.getId());
				listeRdvs = (ArrayList<Rdv>) rdvDao.findAllByUser(unUtilisateur.getId());
				listePrescriptions = (ArrayList<Prescription2>) prescriptionDao.findAllByUser(unUtilisateur.getId());
				listeExamens = (ArrayList<Examen>) examenDao.findAllByUser(unUtilisateur.getId());
				listePrises = (ArrayList<Prise>) priseDao.findAllByUser(unUtilisateur.getId());
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
			ArrayList<Analyse> listeAnalysesTries = new ArrayList<Analyse>();
			DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd");
			int formattedDay = Integer.parseInt(dateDuJour.format(formatterDay));
			DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
			int formattedMonth = Integer.parseInt(dateDuJour.format(formatterMonth));
			DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yyyy");
			int formattedYear = Integer.parseInt(dateDuJour.format(formatterYear));

			for (Analyse analyse : listeAnalyses) { 
				int analyseJour = Integer.parseInt(analyse.getDate().substring(0, 2));
				int analyseMois = Integer.parseInt(analyse.getDate().substring(3, 5));
				int analyseAnnee = Integer.parseInt(analyse.getDate().substring(6, 10));

				if (analyseAnnee>formattedYear) {

					listeAnalysesTries.add(analyse);
				} else if (analyseAnnee==formattedYear) {
					if (analyseMois>formattedMonth) {

						listeAnalysesTries.add(analyse);
					} else if (analyseMois==formattedMonth) {
						if (analyseJour>=formattedDay) {

							listeAnalysesTries.add(analyse);
						}
					}
				}
				System.out.println(listeAnalysesTries);
			} 
			//
			//trier examen
			ArrayList<Examen> listeExamensTries = new ArrayList<Examen>();
			for (Examen examen : listeExamens) { 
				int examenJour = Integer.parseInt(examen.getDate().substring(0, 2));
				int examenMois = Integer.parseInt(examen.getDate().substring(3, 5));
				int examenAnnee = Integer.parseInt(examen.getDate().substring(6, 10));
				if (examenAnnee>formattedYear) {
					listeExamensTries.add(examen);
				} else if (examenAnnee==formattedYear) {
					if (examenMois>formattedMonth) {
						listeExamensTries.add(examen);
					} else if (examenMois==formattedMonth) {
						if (examenJour>=formattedDay) {
							listeExamensTries.add(examen);
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
			//trier prescription
			ArrayList<Prescription2> listePrescriptionsTries = new ArrayList<Prescription2>();
			System.out.println("jour: "+formattedDay);
			System.out.println("mois: "+formattedMonth);
			System.out.println("annee: "+formattedYear);





			for (Prescription2 prescription : listePrescriptions) { 
				int prescriptionJour = Integer.parseInt(prescription.getDateFin().substring(0, 2));
				int prescriptionMois = Integer.parseInt(prescription.getDateFin().substring(3, 5));
				int prescriptionAnnee = Integer.parseInt(prescription.getDateFin().substring(6, 10));


				System.out.println("jour2: "+prescription.getDateFin().substring(0, 2));
				System.out.println("mois2: "+prescription.getDateFin().substring(3, 5));
				System.out.println("annee2: "+prescription.getDateFin().substring(6, 10));
				System.out.println("jour3: "+prescriptionJour);
				System.out.println("mois3: "+prescriptionMois);
				System.out.println("annee3: "+prescriptionAnnee);

				if (prescriptionAnnee>formattedYear) {
					System.out.println("ici1");
					listePrescriptionsTries.add(prescription);
				} else if (prescriptionAnnee==formattedYear) {
					if (prescriptionMois>formattedMonth) {
						System.out.println("ici2");
						listePrescriptionsTries.add(prescription);
					} else if (prescriptionMois==formattedMonth) {
						if (prescriptionJour>=formattedDay) {
							System.out.println("ici3");
							listePrescriptionsTries.add(prescription);
						}
					}
				}
			} 
			//

			request.setAttribute("listeAnalyses", listeAnalysesTries);
			request.setAttribute("listePrescriptions", listePrescriptionsTries);
			request.setAttribute("listeExamens", listeExamensTries);
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