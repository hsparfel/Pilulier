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
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.entities.Medicament;
import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;

@WebServlet("/AfficherMedicament")
public class AfficherMedicamentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String JSP_PAGE = "/WEB-INF/AfficherMedicament.jsp";
	private DAOFactory daoFactory;
	private MedicamentDAO medicamentDao;

	@Override
	public void init() throws ServletException {
		try {
			daoFactory = DAOFactory.getInstance();
			medicamentDao = daoFactory.getMedicamentDAO();
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
		int idMedicament = (Integer) Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		if (session.getAttribute("login") != null) {
			Medicament maMedicament = null;
			ArrayList<Medicament> listeMedicaments = null;
			try {
				listeMedicaments = (ArrayList<Medicament>) medicamentDao.findAll();
				maMedicament = medicamentDao.findByRef(idMedicament);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			request.setAttribute("listeMedicaments", listeMedicaments);
			request.setAttribute("maMedicament", maMedicament);
			this.getServletContext().getRequestDispatcher(JSP_PAGE).forward(request, response);
		} else {
			response.sendRedirect("Accueil");
		}
	}
}
