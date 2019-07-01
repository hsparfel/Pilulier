package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.PatientMedecinDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.entities.PatientMedecin;
import fr.medoc.exception.DAOException;

public class PatientMedecinDAOImpl implements PatientMedecinDAO {

	private ArrayList<PatientMedecin> listePatientMedecins;
	private ArrayList<PatientMedecin> listePatientMedecinsTries;

	private final String ORDRE_INSERT = "insert into utilisateur_medecin (id_utilisateur,id_medecin) values (?,?)";
	//private final String VALUES_INSERT = "(?,?)";
	private final String ORDRE_DELETE = "delete from utilisateur_medecin where id_utilisateur = ? AND id_medecin=?";
	private final String ORDRE_FINDALLBYUSER = "select * from utilisateur_medecin AS um where um.id_utilisateur=?";

	private DAOFactory daoFactory;

	public PatientMedecinDAOImpl(DAOFactory daoFactory) {
		listePatientMedecins = new ArrayList<PatientMedecin>();
		listePatientMedecinsTries = new ArrayList<PatientMedecin>();
		this.daoFactory = daoFactory;
	}

	@Override
	public void supprimerPatientMedecin(PatientMedecin unPatientMedecin) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();

			PreparedStatement pst = connexion.prepareStatement(ORDRE_DELETE);
			pst.setInt(1, unPatientMedecin.getPatient().getId());
			pst.setInt(2, unPatientMedecin.getMedecin().getId());
			pst.executeUpdate();
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void ajouterPatientMedecin(PatientMedecin unPatientMedecin) throws DAOException {

		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			//getListePatientMedecins().add(unPatientMedecin);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT);
			pst.setInt(1, unPatientMedecin.getPatient().getId());
			pst.setInt(2, unPatientMedecin.getMedecin().getId());

			pst.executeUpdate();
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Collection<PatientMedecin> findAllByUser(int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLBYUSER);
			pst.setInt(1, id);
			ResultSet resultSet = pst.executeQuery();
			listePatientMedecinsTries.removeAll(listePatientMedecinsTries);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listePatientMedecinsTries;
	}

	public void setListePatientMedecins(ArrayList<PatientMedecin> listePatientMedecins) {
		this.listePatientMedecins = listePatientMedecins;
	}

	public Collection<PatientMedecin> getListePatientMedecins() {
		return listePatientMedecins;
	}

	public ArrayList<PatientMedecin> getListePatientMedecinsTries() {
		return listePatientMedecinsTries;
	}

	public void setListePatientMedecinsTries(ArrayList<PatientMedecin> listePatientMedecinsTries) {
		this.listePatientMedecinsTries = listePatientMedecinsTries;
	}

	private void resultSetToArrayList(ResultSet resultSet) throws SQLException, DAOException {

		while (resultSet.next()) {
			PatientMedecin a = new PatientMedecin();
			UtilisateurDAO unUtilisateurDAO = daoFactory.getUtilisateurDAO();
			MedecinDAO unMedecinDAO = daoFactory.getMedecinDAO();

			a.setPatient(unUtilisateurDAO.findByRef(resultSet.getInt("id_utilisateur")));
			a.setMedecin(unMedecinDAO.findByRef(resultSet.getInt("id_medicament")));
			getListePatientMedecinsTries().add(a);
		}
	}
}
