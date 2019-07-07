package fr.medoc.servlets.page;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.ProfilDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.entities.Profil;
import fr.medoc.entities.Utilisateur;
import fr.medoc.enumeration.EnumDuree;
import fr.medoc.enumeration.EnumSexe;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/ModifierProfil")
public class ModifierProfilServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String JSP_PAGE = "/WEB-INF/ModifierProfil.jsp";
	private DAOFactory daoFactory;
	private ProfilDAO profilDao;
	private UtilisateurDAO utilisateurDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			profilDao = daoFactory.getProfilDAO();
			utilisateurDao = daoFactory.getUtilisateurDAO();
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
			ArrayList<Profil> listeProfils = null;
			Utilisateur unUtilisateur = null;
			Profil profilRecent=null;
			try {
				unUtilisateur = utilisateurDao.findByName((String) session.getAttribute("login"));
				listeProfils = (ArrayList<Profil>) profilDao.findAllByUser(unUtilisateur.getId());
			} catch (DAOException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeProfils", listeProfils);
			//extraire le plus recent de cette liste et l'envoyer 
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
			EnumSexe[] listeSexes =  EnumSexe.values();
			request.setAttribute("listeSexes", listeSexes);
			request.setAttribute("profil", profilRecent);
			request.setAttribute("utilisateur", unUtilisateur);
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} else {
			response.sendRedirect("ModifierProfil");
		}
	}
}
