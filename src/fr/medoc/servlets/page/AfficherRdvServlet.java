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
import fr.medoc.dao.RdvDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Rdv;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherRdv")
public class AfficherRdvServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String JSP_PAGE = "/WEB-INF/AfficherRdv.jsp";
	private DAOFactory daoFactory;
	private RdvDAO rdvDao;
	private UtilisateurDAO utilisateurDao;
	private MedecinDAO medecinDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			rdvDao = daoFactory.getRdvDAO();
			utilisateurDao = daoFactory.getUtilisateurDAO();
			medecinDao = daoFactory.getMedecinDAO();
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
		int idRdv = (Integer) Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		if (session.getAttribute("login") != null) {
			Rdv myRdv = null;
			ArrayList<Rdv> listeRdvs = null;
			ArrayList<Medecin> listeMedecins = null;
			Utilisateur unUtilisateur = null;
			try {
				unUtilisateur = utilisateurDao.findByName((String) session.getAttribute("login"));
				System.out.println((String) session.getAttribute("login"));
				listeRdvs = (ArrayList<Rdv>) rdvDao.findAllByUser(unUtilisateur.getId());
				listeMedecins = (ArrayList<Medecin>) medecinDao.findAllByUser(unUtilisateur.getId());
				myRdv = rdvDao.findByRef(idRdv);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeRdvs", listeRdvs);
			request.setAttribute("listeMedecins", listeMedecins);
			request.setAttribute("myRdv", myRdv);
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} else {
			response.sendRedirect("Accueil");
		}
	}
}
