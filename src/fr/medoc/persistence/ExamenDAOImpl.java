package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import fr.medoc.dao.ExamenDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Examen;
import fr.medoc.exception.DAOException;

public class ExamenDAOImpl implements ExamenDAO {

	private ArrayList<Examen> listeExamens;
	private final String ORDRE_INSERT = "insert into examen(Nom) values ";
	private final String VALUES_INSERT = "(?)";
	private final String ORDRE_DELETE = "delete from examen where Id = ";
	private final String ORDRE_FINDALL = "select Id,Nom from examen";
	private final String ORDRE_FINDBYREF = "select Id,Nom from examen where Id = ?";
	private final String ORDRE_FINDBYNAME = "select Id,Nom from examen where Nom = ?";
	private final String ORDRE_UPDATE = "update examen set Nom=? where id = ?";
	private DAOFactory daoFactory;

	public ExamenDAOImpl(DAOFactory daoFactory) {
		listeExamens = new ArrayList<Examen>();
		this.daoFactory = daoFactory;
	}

	@Override
	public void modifierExamen(Examen uneExamen, int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeExamens().add(uneExamen);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_UPDATE);
			pst.setString(1, uneExamen.getNom());
			pst.setInt(2, id);
			pst.executeUpdate();
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void ajouterExamen(Examen uneExamen) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeExamens().add(uneExamen);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, uneExamen.getNom());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				uneExamen.setId(rs.getInt(1));
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
		Examen uneExamen = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneExamen = new Examen(rs.getString("nom"));
				uneExamen.setId(id);
			} else {
				throw new DAOException("Erreur recherche d'un examen. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneExamen;
	}

	@Override
	public Examen findByName(String nom) throws DAOException {
		Examen uneExamen = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYNAME);
			pst.setString(1, nom);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneExamen = new Examen(rs.getString("nom"));
				uneExamen.setId(rs.getInt("id"));
			} else {
				throw new DAOException("Erreur recherche d'un examen. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneExamen;
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

	private void resultSetToArrayList(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			Examen a = new Examen();
			a.setId(resultSet.getInt("id"));
			a.setNom(resultSet.getString("nom"));
			getListeExamens().add(a);
		}
	}
}
