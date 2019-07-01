package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import fr.medoc.dao.ExamenDAO;
import fr.medoc.dao.OrdonnanceDAO;
import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Examen;
import fr.medoc.enumeration.EnumExamen;
import fr.medoc.exception.DAOException;

public class ExamenDAOImpl implements ExamenDAO {

	private ArrayList<Examen> listeExamens;
	private final String ORDRE_INSERT = "insert into examen(id_ordonnance, nom, id_cabinet, commentaire, date) values ";
	private final String VALUES_INSERT = "(?,?,?,?,?)";
	private final String ORDRE_DELETE = "delete from examen where Id = ";
	private final String ORDRE_FINDALL = "select * from examen";
	private final String ORDRE_FINDBYREF = "select * from examen where Id = ?";
	private final String ORDRE_UPDATE = "update examen set id_ordonnance=?, set nom=?,set id_cabinet=?,set commentaire=?, set date=? where id = ?";
	private DAOFactory daoFactory;

	public ExamenDAOImpl(DAOFactory daoFactory) {
		listeExamens = new ArrayList<Examen>();
		this.daoFactory = daoFactory;
	}

	@Override
	public void modifierExamen(Examen unExamen, int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeExamens().add(unExamen);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_UPDATE);
			pst.setInt(1, unExamen.getOrdonnance().getId());
			pst.setString(2, unExamen.getNom().name());
			pst.setInt(3, unExamen.getCabinet().getId());
			pst.setString(4, unExamen.getCommentaire());
			pst.setString(5, unExamen.getDate());
			pst.setInt(6, unExamen.getId());
			pst.executeUpdate();
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void ajouterExamen(Examen unExamen) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeExamens().add(unExamen);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, unExamen.getOrdonnance().getId());
			pst.setString(2, unExamen.getNom().name());
			pst.setInt(3, unExamen.getCabinet().getId());
			pst.setString(4, unExamen.getCommentaire());
			pst.setString(5, unExamen.getDate());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				unExamen.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un examen. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void supprimerExamen(int idExamen) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'" + idExamen + "'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Examen findByRef(int id) throws DAOException {
		Examen unExamen = null;
		Connection connexion = null;
		OrdonnanceDAO uneOrdonnanceDAO = daoFactory.getOrdonnanceDAO();
		CabinetDAO unCabinetDAO = daoFactory.getCabinetDAO();
		
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unExamen = new Examen();
				unExamen.setId(id);
				unExamen.setOrdonnance(uneOrdonnanceDAO.findByRef(rs.getInt("id_ordonnance")));
				unExamen.setNom(EnumExamen.valueOf(rs.getString("nom")));
				unExamen.setCabinet(unCabinetDAO.findByRef(rs.getInt("id_cabinet")));
				unExamen.setCommentaire(rs.getString("commentaire"));
				unExamen.setDate(rs.getString("date"));
			} else {
				throw new DAOException("Erreur recherche d'un examen. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unExamen;
	}

	@Override
	public Collection<Examen> findAll() throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeExamens.removeAll(listeExamens);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeExamens;
	}

	public void setListeExamens(ArrayList<Examen> listeExamens) {
		this.listeExamens = listeExamens;
	}

	public Collection<Examen> getListeExamens() {
		return listeExamens;
	}

	private void resultSetToArrayList(ResultSet resultSet) throws SQLException, DAOException {
		while (resultSet.next()) {
			Examen a = new Examen();
			OrdonnanceDAO uneOrdonnanceDAO = daoFactory.getOrdonnanceDAO();
			CabinetDAO unCabinetDAO = daoFactory.getCabinetDAO();
			
			a.setId(resultSet.getInt("id"));
			a.setOrdonnance(uneOrdonnanceDAO.findByRef(resultSet.getInt("id_ordonnance")));
			a.setNom(EnumExamen.valueOf(resultSet.getString("nom")));
			a.setCabinet(unCabinetDAO.findByRef(resultSet.getInt("id_cabinet")));
			a.setCommentaire(resultSet.getString("commentaire"));
			a.setDate(resultSet.getString("date"));
			getListeExamens().add(a);
		}
	}
}
