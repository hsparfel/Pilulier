package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Prescription;
import fr.medoc.exception.DAOException;

public class MedicamentDAOImpl implements MedicamentDAO {

	private ArrayList<Medicament> listeMedicaments;
	private ArrayList<Medicament> listeMedicamentsTries;
	private ArrayList<Medicament> listeMedicamentsExclus;

	private final String ORDRE_INSERT = "insert into medicament(Nom) values ";
	private final String VALUES_INSERT = "(?)";
	private final String ORDRE_DELETE = "delete from medicament where Id = ";
	private final String ORDRE_FINDALL = "select Id,Nom from medicament";
	private final String ORDRE_FINDBYREF = "select Id,Nom from medicament where Id = ?";
	private final String ORDRE_FINDBYNAME = "select Id,Nom from medicament where Nom = ?";
	private final String ORDRE_FINDALLBYUSER = "select m.id,m.nom  from medicament AS m, utilisateur_medicament AS um where m.id=um.id_medicament AND um.id_utilisateur=?";

	private final String ORDRE_FINDALLFILTERED = "select m.id,m.nom from medicament AS m where m.nom NOT IN (select m.nom from medicament AS m, utilisateur AS u, utilisateur_medicament AS um where m.id=um.id_medicament AND u.id=um.id_utilisateur AND u.nom=?)";

	private DAOFactory daoFactory;

	public MedicamentDAOImpl(DAOFactory daoFactory) {
		listeMedicaments = new ArrayList<Medicament>();
		listeMedicamentsTries = new ArrayList<Medicament>();
		listeMedicamentsExclus = new ArrayList<Medicament>();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouterMedicament(Medicament unMedicament) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListeMedicaments().add(unMedicament);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, unMedicament.getNom());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				unMedicament.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un medicament. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void supprimerMedicament(int idMedicament) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'" + idMedicament + "'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Medicament findByRef(int id) throws DAOException {
		Medicament unMedicament = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unMedicament = new Medicament(rs.getString("nom"));
				unMedicament.setId(id);
			} else {
				throw new DAOException("Erreur recherche d'un medicament. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unMedicament;
	}

	@Override
	public Medicament findByName(String nom) throws DAOException {
		Medicament unMedicament = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYNAME);
			pst.setString(1, nom);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unMedicament = new Medicament(rs.getString("nom"));
				unMedicament.setId(rs.getInt("id"));
			} else {
				throw new DAOException("Erreur recherche d'un medicament. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unMedicament;
	}

	@Override
	public Collection<Medicament> findAll() throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeMedicaments.removeAll(listeMedicaments);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeMedicaments;
	}

	public void setListeMedicaments(ArrayList<Medicament> listeMedicaments) {
		this.listeMedicaments = listeMedicaments;
	}

	public void setListeMedicamentsTries(ArrayList<Medicament> listeMedicamentsTries) {
		this.listeMedicamentsTries = listeMedicamentsTries;
	}

	public void setListeMedicamentsExclus(ArrayList<Medicament> listeMedicamentsExclus) {
		this.listeMedicamentsExclus = listeMedicamentsExclus;
	}

	public Collection<Medicament> getListeMedicaments() {
		return listeMedicaments;
	}

	public Collection<Medicament> getListeMedicamentsTries() {
		return listeMedicamentsTries;
	}

	public Collection<Medicament> getListeMedicamentsExclus() {
		return listeMedicamentsExclus;
	}

	private void resultSetToArrayList(ResultSet resultSet) throws SQLException {

		while (resultSet.next()) {
			Medicament a = new Medicament();
			a.setId(resultSet.getInt("id"));
			a.setNom(resultSet.getString("nom"));
			getListeMedicaments().add(a);
		}
	}

	@Override
	public Collection<Medicament> findAllExcludedByUser(String unUtilisateur) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLFILTERED);
			pst.setString(1, unUtilisateur);
			ResultSet resultSet = pst.executeQuery();
			listeMedicamentsExclus.removeAll(listeMedicamentsExclus);
			resultSetToArrayListExcluded(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeMedicamentsExclus;
	}

	private void resultSetToArrayListExcluded(ResultSet resultSet) throws SQLException {

		while (resultSet.next()) {
			Medicament a = new Medicament();
			a.setId(resultSet.getInt("id"));
			a.setNom(resultSet.getString("nom"));
			getListeMedicamentsTries().add(a);
		}
	}

	@Override
	public Collection<Medicament> findAllByUser(int unUtilisateur) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLBYUSER);
			pst.setInt(1, unUtilisateur);
			ResultSet resultSet = pst.executeQuery();
			listeMedicamentsTries.removeAll(listeMedicamentsTries);
			resultSetToArrayListFiltered(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeMedicamentsTries;
	}

	private void resultSetToArrayListFiltered(ResultSet resultSet) throws SQLException {

		while (resultSet.next()) {
			Medicament a = new Medicament();
			a.setId(resultSet.getInt("id"));
			a.setNom(resultSet.getString("nom"));
			getListeMedicamentsTries().add(a);
		}
	}
}
