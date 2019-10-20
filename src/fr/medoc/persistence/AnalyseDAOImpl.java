package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import fr.medoc.dao.AnalyseDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Analyse;
import fr.medoc.exception.DAOException;

public class AnalyseDAOImpl implements AnalyseDAO {

	private ArrayList<Analyse> listeAnalyses;
	private final String ORDRE_INSERT = "insert into analyse(Nom) values ";
	private final String VALUES_INSERT = "(?)";
	private final String ORDRE_DELETE = "delete from analyse where Id = ";
	private final String ORDRE_FINDALL = "select Id,Nom from analyse";
	private final String ORDRE_FINDBYREF = "select Id,Nom from analyse where Id = ?";
	private final String ORDRE_FINDBYNAME = "select Id,Nom from analyse where Nom = ?";
	private final String ORDRE_UPDATE = "update analyse set Nom=? where id = ?";
	private DAOFactory daoFactory;

	public AnalyseDAOImpl(DAOFactory daoFactory) {
		listeAnalyses = new ArrayList<Analyse>();
		this.daoFactory = daoFactory;
	}

	@Override
	public void modifierAnalyse(Analyse uneAnalyse, int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeAnalyses().add(uneAnalyse);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_UPDATE);
			pst.setString(1, uneAnalyse.getNom());
			pst.setInt(2, id);
			pst.executeUpdate();
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void ajouterAnalyse(Analyse uneAnalyse) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeAnalyses().add(uneAnalyse);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, uneAnalyse.getNom());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				uneAnalyse.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un analyse. ");
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
	public Analyse findByRef(int id) throws DAOException {
		Analyse uneAnalyse = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneAnalyse = new Analyse(rs.getString("nom"));
				uneAnalyse.setId(id);
			} else {
				throw new DAOException("Erreur recherche d'un analyse. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneAnalyse;
	}

	@Override
	public Analyse findByName(String nom) throws DAOException {
		Analyse uneAnalyse = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYNAME);
			pst.setString(1, nom);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneAnalyse = new Analyse(rs.getString("nom"));
				uneAnalyse.setId(rs.getInt("id"));
			} else {
				throw new DAOException("Erreur recherche d'un analyse. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneAnalyse;
	}

	@Override
	public Collection<Analyse> findAll() throws DAOException {
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

	public void setListeAnalyses(ArrayList<Analyse> listeAnalyses) {
		this.listeAnalyses = listeAnalyses;
	}

	public Collection<Analyse> getListeAnalyses() {
		return listeAnalyses;
	}

	private void resultSetToArrayList(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			Analyse a = new Analyse();
			a.setId(resultSet.getInt("id"));
			a.setNom(resultSet.getString("nom"));
			getListeAnalyses().add(a);
		}
	}
}
