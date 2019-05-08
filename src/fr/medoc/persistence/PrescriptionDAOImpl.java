package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.PrescriptionDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Dose;
import fr.medoc.entities.Frequence;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Prescription;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOException;


public class PrescriptionDAOImpl implements PrescriptionDAO{
	
	private ArrayList<Prescription> listePrescriptions;
	private ArrayList<Prescription> listePrescriptionsTries;
	
	
	private final String ORDRE_INSERT = "insert into utilisateur_medicament values ";
	private final String VALUES_INSERT = "(?,?,?,?,?,?)";
	
	private final String ORDRE_FINDALLBYUSER = "select * from utilisateur_medicament AS um where um.id_utilisateur=?";
	
    private DAOFactory          daoFactory;

	public PrescriptionDAOImpl(DAOFactory daoFactory) {
		listePrescriptions = new ArrayList<Prescription>();
		listePrescriptionsTries = new ArrayList<Prescription>();
		this.daoFactory = daoFactory;
	}
	@Override
	public void ajouterPrescription(Prescription unePrescription) throws DAOException{
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListePrescriptions().add(unePrescription);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT);
			pst.setInt(1, unePrescription.getIdUtilisateur());
			pst.setInt(2, unePrescription.getIdMedicament());
			pst.setInt(3, unePrescription.getNbDose());
			pst.setInt(4, unePrescription.getIdDose());
			pst.setInt(5, unePrescription.getNbFrequence());
			pst.setInt(6, unePrescription.getIdFrequence());
			pst.executeUpdate();
			//rs = pst.getGeneratedKeys();
			//if (rs.next()){
			//	unMedicament.setId(rs.getInt(1));
			//} else {s
			//	throw new DAOException("Erreur création d'un medicament. " );
			//}
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
	private void resultSetToArrayList(ResultSet resultSet)
			throws SQLException {

		while (resultSet.next()) {
			Prescription a = new Prescription();
			a.setIdUtilisateur(resultSet.getInt("id_utilisateur"));
			a.setIdMedicament(resultSet.getInt("id_medicament"));
			a.setNbDose(resultSet.getInt("nb_dose"));
			a.setIdDose(resultSet.getInt("id_dose"));
			a.setNbFrequence(resultSet.getInt("nb_frequence"));
			a.setIdFrequence(resultSet.getInt("id_frequence"));
			getListePrescriptionsTries().add(a);
		}
	}
	
	
	

}
