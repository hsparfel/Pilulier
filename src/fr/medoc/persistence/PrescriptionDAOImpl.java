package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.PrescriptionDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.DureeDAO;
import fr.medoc.dao.FrequenceDAO;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.entities.Prescription;
import fr.medoc.exception.DAOException;

public class PrescriptionDAOImpl implements PrescriptionDAO {

	private ArrayList<Prescription> listePrescriptions;
	private ArrayList<Prescription> listePrescriptionsTries;

	private final String ORDRE_INSERT = "insert into prescription(id_utilisateur,id_medicament,id_medecin,nb_dose,id_dose,nb_frequence,id_frequence,matin,midi,soir,nb_duree,id_duree,date_debut,date_fin) values ";
	private final String VALUES_INSERT = "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String ORDRE_FINDALL = "select * from prescription";
	private final String ORDRE_FINDBYREF = "select * from prescription where Id = ?";
	private final String ORDRE_FINDALLBYUSER = "select * from prescription AS um where um.id_utilisateur=?";
	private final String ORDRE_FINDBYREFS = "select * from prescription AS um where um.id_utilisateur=? AND um.id_medicament=?";
	private final String ORDRE_DELETE = "delete from prescription where Id = ";
	private final String ORDRE_UPDATE = "update prescription set id_utilisateur=?,id_medicament=?,id_medecin=?,nb_dose=?,id_dose=?,nb_frequence=?,id_frequence=?,matin=?,midi=?,soir=?,nb_duree=?,id_duree=?,date_debut=?,date_fin=?  where id = ?";

	private DAOFactory daoFactory;

	public PrescriptionDAOImpl(DAOFactory daoFactory) {
		listePrescriptions = new ArrayList<Prescription>();
		listePrescriptionsTries = new ArrayList<Prescription>();
		this.daoFactory = daoFactory;
	}

	@Override
	public Prescription findByRef(int id) throws DAOException {
		Prescription unPrescription = null;
		Connection connexion = null;
		UtilisateurDAO unUtilisateurDAO = daoFactory.getUtilisateurDAO();
		MedecinDAO unMedecinDAO = daoFactory.getMedecinDAO();
		MedicamentDAO unMedicamentDAO = daoFactory.getMedicamentDAO();
		DoseDAO unDoseDAO = daoFactory.getDoseDAO();
		DureeDAO unDureeDAO = daoFactory.getDureeDAO();
		FrequenceDAO unFrequenceDAO = daoFactory.getFrequenceDAO();
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unPrescription = new Prescription();

				unPrescription.setId(rs.getInt("id"));
				unPrescription.setUtilisateur(unUtilisateurDAO.findByRef(rs.getInt("id_utilisateur")));
				unPrescription.setMedecin(unMedecinDAO.findByRef(rs.getInt("id_medecin")));
				unPrescription.setMedicament(unMedicamentDAO.findByRef(rs.getInt("id_medicament")));
				unPrescription.setDose(unDoseDAO.findByRef(rs.getInt("id_dose")));
				unPrescription.setDuree(unDureeDAO.findByRef(rs.getInt("id_duree")));
				unPrescription.setFrequence(unFrequenceDAO.findByRef(rs.getInt("id_frequence")));
				unPrescription.setNbDose(rs.getInt("nb_dose"));
				unPrescription.setNbDuree(rs.getInt("nb_duree"));
				unPrescription.setNbFrequence(rs.getInt("nb_frequence"));
				unPrescription.setMatin(rs.getInt("matin"));
				unPrescription.setMidi(rs.getInt("midi"));
				unPrescription.setSoir(rs.getInt("soir"));
				unPrescription.setDateDebut(rs.getString("date_debut"));
				unPrescription.setDateFin(rs.getString("date_fin"));
			} else {
				throw new DAOException("Erreur recherche d'un prescription");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unPrescription;
	}

	@Override
	public void modifierPrescription(Prescription unePrescription, int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListePrescriptions().add(unePrescription);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_UPDATE);
			pst.setInt(1, unePrescription.getUtilisateur().getId());
			pst.setInt(2, unePrescription.getMedicament().getId());
			pst.setInt(3, unePrescription.getMedecin().getId());
			pst.setInt(4, unePrescription.getNbDose());
			pst.setInt(5, unePrescription.getDose().getId());
			pst.setInt(6, unePrescription.getNbFrequence());
			pst.setInt(7, unePrescription.getFrequence().getId());
			pst.setInt(8, unePrescription.getMatin());
			pst.setInt(9, unePrescription.getMidi());
			pst.setInt(10, unePrescription.getSoir());
			pst.setInt(11, unePrescription.getNbDuree());
			pst.setInt(12, unePrescription.getDuree().getId());
			pst.setString(13, unePrescription.getDateDebut());
			pst.setString(14, unePrescription.getDateFin());
			pst.setInt(15, id);
			pst.executeUpdate();
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void supprimerPrescription(int idPrescription) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'" + idPrescription + "'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void ajouterPrescription(Prescription unePrescription) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListePrescriptions().add(unePrescription);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, unePrescription.getUtilisateur().getId());
			pst.setInt(2, unePrescription.getMedicament().getId());
			pst.setInt(3, unePrescription.getMedecin().getId());
			pst.setInt(4, unePrescription.getNbDose());
			pst.setInt(5, unePrescription.getDose().getId());
			pst.setInt(6, unePrescription.getNbFrequence());
			pst.setInt(7, unePrescription.getFrequence().getId());
			pst.setInt(8, unePrescription.getMatin());
			pst.setInt(9, unePrescription.getMidi());
			pst.setInt(10, unePrescription.getSoir());
			pst.setInt(11, unePrescription.getNbDuree());
			pst.setInt(12, unePrescription.getDuree().getId());
			pst.setString(13, unePrescription.getDateDebut());
			pst.setString(14, unePrescription.getDateFin());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				unePrescription.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'une prescription. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Collection<Prescription> findAllByUser(int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLBYUSER);
			pst.setInt(1, id);
			ResultSet resultSet = pst.executeQuery();
			listePrescriptionsTries.removeAll(listePrescriptionsTries);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listePrescriptionsTries;
	}

	public void setListePrescriptions(ArrayList<Prescription> listePrescriptions) {
		this.listePrescriptions = listePrescriptions;
	}

	public Collection<Prescription> getListePrescriptions() {
		return listePrescriptions;
	}

	public ArrayList<Prescription> getListePrescriptionsTries() {
		return listePrescriptionsTries;
	}

	public void setListePrescriptionsTries(ArrayList<Prescription> listePrescriptionsTries) {
		this.listePrescriptionsTries = listePrescriptionsTries;
	}

	private void resultSetToArrayList(ResultSet resultSet) throws SQLException, DAOException {

		while (resultSet.next()) {
			Prescription a = new Prescription();
			UtilisateurDAO unUtilisateurDAO = daoFactory.getUtilisateurDAO();
			MedicamentDAO unMedicamentDAO = daoFactory.getMedicamentDAO();
			DoseDAO uneDoseDAO = daoFactory.getDoseDAO();
			DureeDAO uneDureeDAO = daoFactory.getDureeDAO();
			FrequenceDAO uneFrequenceDAO = daoFactory.getFrequenceDAO();
			a.setId(resultSet.getInt("id"));
			a.setUtilisateur(unUtilisateurDAO.findByRef(resultSet.getInt("id_utilisateur")));
			a.setMedicament(unMedicamentDAO.findByRef(resultSet.getInt("id_medicament")));
			a.setNbDose(resultSet.getInt("nb_dose"));
			a.setDose(uneDoseDAO.findByRef(resultSet.getInt("id_dose")));
			a.setNbFrequence(resultSet.getInt("nb_frequence"));
			a.setFrequence(uneFrequenceDAO.findByRef(resultSet.getInt("id_frequence")));
			a.setNbDuree(resultSet.getInt("nb_duree"));
			a.setDuree(uneDureeDAO.findByRef(resultSet.getInt("id_duree")));
			a.setMatin(resultSet.getInt("matin"));
			a.setMidi(resultSet.getInt("midi"));
			a.setSoir(resultSet.getInt("soir"));
			a.setDateDebut(resultSet.getString("date_debut"));
			a.setDateFin(a.calculerDateFin(resultSet.getString("date_debut"), a.getNbDuree(), a.getDuree()));
			getListePrescriptionsTries().add(a);
		}
	}

	@Override
	public Prescription findByRefs(int idUtilisateur, int idMedicament) throws DAOException {
		Prescription unePrescription = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREFS);
			pst.setInt(1, idUtilisateur);
			pst.setInt(2, idMedicament);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unePrescription = new Prescription();
				UtilisateurDAO unUtilisateurDAO = daoFactory.getUtilisateurDAO();
				MedecinDAO unMedecinDAO = daoFactory.getMedecinDAO();
				MedicamentDAO unMedicamentDAO = daoFactory.getMedicamentDAO();
				DoseDAO uneDoseDAO = daoFactory.getDoseDAO();

				FrequenceDAO uneFrequenceDAO = daoFactory.getFrequenceDAO();

				unePrescription.setUtilisateur(unUtilisateurDAO.findByRef(rs.getInt("id_utilisateur")));
				unePrescription.setMedecin(unMedecinDAO.findByRef(rs.getInt("id_medecin")));

				unePrescription.setMedicament(unMedicamentDAO.findByRef(rs.getInt("id_medicament")));
				unePrescription.setNbDose(rs.getInt("nb_dose"));
				unePrescription.setDose(uneDoseDAO.findByRef(rs.getInt("id_dose")));
				unePrescription.setNbFrequence(rs.getInt("nb_frequence"));
				unePrescription.setFrequence(uneFrequenceDAO.findByRef(rs.getInt("id_frequence")));

			} else {
				throw new DAOException("Erreur recherche d'une prescription. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unePrescription;
	}
}
