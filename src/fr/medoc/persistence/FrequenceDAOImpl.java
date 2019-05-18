package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.FrequenceDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Frequence;
import fr.medoc.exception.DAOException;

public class FrequenceDAOImpl implements FrequenceDAO {

	private ArrayList<Frequence> listeFrequences;
	private final String ORDRE_INSERT = "insert into frequence(Nom) values ";
	private final String VALUES_INSERT = "(?)";
	private final String ORDRE_DELETE = "delete from frequence where Id = ";
	private final String ORDRE_FINDALL = "select Id,Nom from frequence";
	private final String ORDRE_FINDBYREF = "select Id,Nom from frequence where Id = ?";
	private final String ORDRE_FINDBYNAME = "select Id,Nom from frequence where Nom = ?";
	private final String ORDRE_UPDATE = "update frequence set Nom=? where id = ?";
	private DAOFactory daoFactory;

	public FrequenceDAOImpl(DAOFactory daoFactory) {
		listeFrequences = new ArrayList<Frequence>();
		this.daoFactory = daoFactory;
	}

	@Override
	public void modifierFrequence(Frequence uneFrequence, int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeFrequences().add(uneFrequence);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_UPDATE);
			pst.setString(1, uneFrequence.getNom());
			pst.setInt(2, id);
			pst.executeUpdate();
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void ajouterFrequence(Frequence uneFrequence) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeFrequences().add(uneFrequence);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, uneFrequence.getNom());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				uneFrequence.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un dose. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void supprimerFrequence(int idFrequence) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'" + idFrequence + "'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Frequence findByRef(int id) throws DAOException {
		Frequence uneFrequence = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneFrequence = new Frequence(rs.getString("nom"));
				uneFrequence.setId(id);
			} else {
				throw new DAOException("Erreur recherche d'un dose. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneFrequence;
	}

	@Override
	public Frequence findByName(String nom) throws DAOException {
		Frequence uneFrequence = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYNAME);
			pst.setString(1, nom);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneFrequence = new Frequence(rs.getString("nom"));
				uneFrequence.setId(rs.getInt("id"));
			} else {
				throw new DAOException("Erreur recherche d'un Frequence. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneFrequence;
	}

	@Override
	public Collection<Frequence> findAll() throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeFrequences.removeAll(listeFrequences);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeFrequences;
	}

	public void setListeFrequences(ArrayList<Frequence> listeFrequences) {
		this.listeFrequences = listeFrequences;
	}

	public Collection<Frequence> getListeFrequences() {
		return listeFrequences;
	}

	private void resultSetToArrayList(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			Frequence a = new Frequence();
			a.setId(resultSet.getInt("id"));
			a.setNom(resultSet.getString("nom"));
			getListeFrequences().add(a);
		}
	}
}
