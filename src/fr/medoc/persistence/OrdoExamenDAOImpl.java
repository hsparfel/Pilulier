package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import fr.medoc.dao.OrdoExamenDAO;
import fr.medoc.dao.OrdonnanceDAO;
import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.OrdoAnalyse;
import fr.medoc.entities.OrdoExamen;
import fr.medoc.enumeration.EnumExamen_old;
import fr.medoc.exception.DAOException;

public class OrdoExamenDAOImpl implements OrdoExamenDAO {

	private ArrayList<OrdoExamen> listeExamens;
	private final String ORDRE_INSERT = "insert into ordo_examen(id_ordonnance, nom, id_cabinet, commentaire, date) values ";
	private final String VALUES_INSERT = "(?,?,?,?,?)";
	private final String ORDRE_NEW_INSERT = "insert into ordo_examen(nom) values ";
	private final String VALUES_NEW_INSERT = "(?)";
	private final String ORDRE_DELETE = "delete from ordo_examen where Id = ";
	private final String ORDRE_FINDALL = "select * from ordo_examen";
	private final String ORDRE_FINDBYREF = "select * from ordo_examen where Id = ?";
	private final String ORDRE_FINDBYORDONNANCE = "select * from ordo_examen where Id_ordonnance = ?";
	private final String ORDRE_UPDATE = "update ordo_examen set id_ordonnance=?, set nom=?,set id_cabinet=?,set commentaire=?, set date=? where id = ?";
	//a faire
	private final String ORDRE_FINDALLBYUSER = "select e.id,e.id_ordonnance,e.nom,e.id_cabinet,e.commentaire,e.date from ordo_examen AS e, ordonnance as o where e.id_ordonnance=o.id and o.id_utilisateur=? order by (SUBSTRING(e.date,7,4)), (SUBSTRING(e.date,4,2)), (SUBSTRING(e.date,1,2))";
	
	private DAOFactory daoFactory;

	public OrdoExamenDAOImpl(DAOFactory daoFactory) {
		listeExamens = new ArrayList<OrdoExamen>();
		this.daoFactory = daoFactory;
	}

	@Override
	public void modifierExamen(OrdoExamen unExamen, int id) throws DAOException {
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
	public void ajouterExamen(OrdoExamen unExamen) throws DAOException {
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
	public void ajouterNewExamen(OrdoExamen unExamen) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeExamens().add(unExamen);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_NEW_INSERT + VALUES_NEW_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, unExamen.getNom().name());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				unExamen.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un nouvel examen. ");
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
	public OrdoExamen findByRef(int id) throws DAOException {
		OrdoExamen unExamen = null;
		Connection connexion = null;
		OrdonnanceDAO uneOrdonnanceDAO = daoFactory.getOrdonnanceDAO();
		CabinetDAO unCabinetDAO = daoFactory.getCabinetDAO();

		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unExamen = new OrdoExamen();
				unExamen.setId(id);
				unExamen.setOrdonnance(uneOrdonnanceDAO.findByRef(rs.getInt("id_ordonnance")));
				unExamen.setNom(EnumExamen_old.valueOf(rs.getString("nom")));
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
	public Collection<OrdoExamen> findByOrdonnance(int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYORDONNANCE);
			pst.setInt(1, id);
			ResultSet resultSet = pst.executeQuery();
			listeExamens.removeAll(listeExamens);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeExamens;
	}

	
	@Override
	public Collection<OrdoExamen> findAll() throws DAOException {
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

	public void setListeExamens(ArrayList<OrdoExamen> listeExamens) {
		this.listeExamens = listeExamens;
	}

	public Collection<OrdoExamen> getListeExamens() {
		return listeExamens;
	}

	private void resultSetToArrayList(ResultSet resultSet) throws SQLException, DAOException {
		while (resultSet.next()) {
			OrdoExamen a = new OrdoExamen();
			OrdonnanceDAO uneOrdonnanceDAO = daoFactory.getOrdonnanceDAO();
			CabinetDAO unCabinetDAO = daoFactory.getCabinetDAO();

			a.setId(resultSet.getInt("id"));
			a.setOrdonnance(uneOrdonnanceDAO.findByRef(resultSet.getInt("id_ordonnance")));
			a.setNom(EnumExamen_old.valueOf(resultSet.getString("nom")));
			a.setCabinet(unCabinetDAO.findByRef(resultSet.getInt("id_cabinet")));
			a.setCommentaire(resultSet.getString("commentaire"));
			a.setDate(resultSet.getString("date"));
			getListeExamens().add(a);
		}
	}
	
	@Override
	public Collection<OrdoExamen> findAllByUser(int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLBYUSER);
			pst.setInt(1, id);
			ResultSet resultSet = pst.executeQuery();
			listeExamens.removeAll(listeExamens);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeExamens;
		
	}
}
