package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.PrescriptionDAO;
import fr.medoc.dao.SpecialiteDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.FrequenceDAO;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Prescription;
import fr.medoc.entities.Prise;
import fr.medoc.exception.DAOException;

public class PrescriptionDAOImpl implements PrescriptionDAO {

	private ArrayList<Prescription> listePrescriptions;
	private ArrayList<Prescription> listePrescriptionsTries;

	private final String ORDRE_INSERT = "insert into utilisateur_medicament values ";
	private final String VALUES_INSERT = "(?,?,?,?,?,?,?,?,?,?)";

	private final String ORDRE_FINDALLBYUSER = "select * from utilisateur_medicament AS um where um.id_utilisateur=?";
	private final String ORDRE_FINDBYREFS = "select * from utilisateur_medicament AS um where um.id_utilisateur=? AND um.id_medicament=?";

	
	private DAOFactory daoFactory;

	public PrescriptionDAOImpl(DAOFactory daoFactory) {
		listePrescriptions = new ArrayList<Prescription>();
		listePrescriptionsTries = new ArrayList<Prescription>();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouterPrescription(Prescription unePrescription) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListePrescriptions().add(unePrescription);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT);
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
			pst.executeUpdate();
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
			FrequenceDAO uneFrequenceDAO = daoFactory.getFrequenceDAO();
			
			a.setUtilisateur(unUtilisateurDAO.findByRef(resultSet.getInt("id_utilisateur")));
			a.setMedicament(unMedicamentDAO.findByRef(resultSet.getInt("id_medicament")));
			a.setNbDose(resultSet.getInt("nb_dose"));
			a.setDose(uneDoseDAO.findByRef(resultSet.getInt("id_dose")));
			a.setNbFrequence(resultSet.getInt("nb_frequence"));
			a.setFrequence(uneFrequenceDAO.findByRef(resultSet.getInt("id_frequence")));
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
				throw new DAOException("Erreur recherche d'une prescription. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unePrescription; 
	}
}
