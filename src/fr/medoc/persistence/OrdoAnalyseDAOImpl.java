package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import fr.medoc.dao.OrdoAnalyseDAO;
import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.OrdonnanceDAO;
import fr.medoc.entities.OrdoAnalyse;
import fr.medoc.entities.OrdoExamen;
import fr.medoc.enumeration.EnumAnalyse_old;
import fr.medoc.exception.DAOException;

public class OrdoAnalyseDAOImpl implements OrdoAnalyseDAO {

	private ArrayList<OrdoAnalyse> listeAnalyses;
	private final String ORDRE_INSERT = "insert into ordo_analyse(id_ordonnance, nom, id_cabinet, commentaire, date) values ";
	private final String VALUES_INSERT = "(?,?,?,?,?)";
	private final String ORDRE_NEW_INSERT = "insert into ordo_analyse(nom) values ";
	private final String VALUES_NEW_INSERT = "(?)";
	private final String ORDRE_DELETE = "delete from ordo_analyse where Id = ";
	private final String ORDRE_FINDALL = "select * from ordo_analyse";
	private final String ORDRE_FINDBYREF = "select * from ordo_analyse where Id = ?";
	private final String ORDRE_FINDBYORDONNANCE = "select * from ordo_analyse where Id_ordonnance = ?";
	private final String ORDRE_UPDATE = "update ordo_analyse set id_ordonnance=?, set nom=?,set id_cabinet=?,set commentaire=?, set date=? where id = ?";
	//a faire
	private final String ORDRE_FINDALLBYUSER = "select a.id,a.id_ordonnance,a.nom,a.id_cabinet,a.commentaire,a.date from ordo_analyse AS a, ordonnance as o where a.id_ordonnance=o.id and o.id_utilisateur=? order by (SUBSTRING(a.date,7,4)), (SUBSTRING(a.date,4,2)), (SUBSTRING(a.date,1,2))";
	
	private DAOFactory daoFactory;

	public OrdoAnalyseDAOImpl(DAOFactory daoFactory) {
		listeAnalyses = new ArrayList<OrdoAnalyse>();
		this.daoFactory = daoFactory;
	}

	@Override
	public void modifierAnalyse(OrdoAnalyse uneAnalyse, int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeAnalyses().add(uneAnalyse);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_UPDATE);
			pst.setInt(1, uneAnalyse.getOrdonnance().getId());
			pst.setString(2, uneAnalyse.getNom().name());
			pst.setInt(3, uneAnalyse.getCabinet().getId());
			pst.setString(4, uneAnalyse.getCommentaire());
			pst.setString(5, uneAnalyse.getDate());
			pst.setInt(6, uneAnalyse.getId());
			pst.executeUpdate();
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void ajouterAnalyse(OrdoAnalyse uneAnalyse) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeAnalyses().add(uneAnalyse);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, uneAnalyse.getOrdonnance().getId());
			pst.setString(2, uneAnalyse.getNom().name());
			pst.setInt(3, uneAnalyse.getCabinet().getId());
			pst.setString(4, uneAnalyse.getCommentaire());
			pst.setString(5, uneAnalyse.getDate());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				uneAnalyse.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'une analyse. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	public void ajouterNewAnalyse(OrdoAnalyse uneAnalyse) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeAnalyses().add(uneAnalyse);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_NEW_INSERT + VALUES_NEW_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, uneAnalyse.getNom().name());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				uneAnalyse.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'une analyse. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void supprimerAnalyse(int idAnalyse) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'" + idAnalyse + "'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public OrdoAnalyse findByRef(int id) throws DAOException {
		OrdoAnalyse uneAnalyse = null;
		Connection connexion = null;
		OrdonnanceDAO uneOrdonnanceDAO = daoFactory.getOrdonnanceDAO();
		CabinetDAO unCabinetDAO = daoFactory.getCabinetDAO();
		
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneAnalyse = new OrdoAnalyse();
				uneAnalyse.setId(id);
				uneAnalyse.setOrdonnance(uneOrdonnanceDAO.findByRef(rs.getInt("id_ordonnance")));
				uneAnalyse.setNom(EnumAnalyse_old.valueOf(rs.getString("nom")));
				uneAnalyse.setCabinet(unCabinetDAO.findByRef(rs.getInt("id_cabinet")));
				uneAnalyse.setCommentaire(rs.getString("commentaire"));
				uneAnalyse.setDate(rs.getString("date"));
			} else {
				throw new DAOException("Erreur recherche d'une analyse. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneAnalyse;
	}

	@Override
	public Collection<OrdoAnalyse> findAll() throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeAnalyses.removeAll(listeAnalyses);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeAnalyses;
	}

	public void setListeAnalyses(ArrayList<OrdoAnalyse> listeAnalyses) {
		this.listeAnalyses = listeAnalyses;
	}

	public Collection<OrdoAnalyse> getListeAnalyses() {
		return listeAnalyses;
	}

	private void resultSetToArrayList(ResultSet resultSet) throws SQLException, DAOException {
		while (resultSet.next()) {
			OrdoAnalyse a = new OrdoAnalyse();
			OrdonnanceDAO uneOrdonnanceDAO = daoFactory.getOrdonnanceDAO();
			CabinetDAO unCabinetDAO = daoFactory.getCabinetDAO();
			
			a.setId(resultSet.getInt("id"));
			a.setOrdonnance(uneOrdonnanceDAO.findByRef(resultSet.getInt("id_ordonnance")));
			a.setNom(EnumAnalyse_old.valueOf(resultSet.getString("nom")));
			a.setCabinet(unCabinetDAO.findByRef(resultSet.getInt("id_cabinet")));
			a.setCommentaire(resultSet.getString("commentaire"));
			a.setDate(resultSet.getString("date"));
			getListeAnalyses().add(a);
		}
	}

	@Override
	public Collection<OrdoAnalyse> findAllByUser(int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLBYUSER);
			pst.setInt(1, id);
			ResultSet resultSet = pst.executeQuery();
			listeAnalyses.removeAll(listeAnalyses);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeAnalyses;
		
	}

	@Override
	public Collection<OrdoAnalyse> findByOrdonnance(int id) throws DAOException {
		
			Connection connexion = null;
			try {
				connexion = daoFactory.getConnection();
				PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYORDONNANCE);
				pst.setInt(1, id);
				ResultSet resultSet = pst.executeQuery();
				listeAnalyses.removeAll(listeAnalyses);
				resultSetToArrayList(resultSet);
				daoFactory.closeConnexion(connexion);
			} catch (SQLException e) {
				throw new DAOException(e);
			}
			return listeAnalyses;
		}
		
}
