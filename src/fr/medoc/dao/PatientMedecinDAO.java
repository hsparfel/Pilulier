package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.PatientMedecin;
import fr.medoc.exception.DAOException;


public interface PatientMedecinDAO {

	public void ajouterPatientMedecin (PatientMedecin unPatientMedecin)throws DAOException;
	public Collection<PatientMedecin> findAllByUser(int id) throws DAOException;
	public void supprimerPatientMedecin (PatientMedecin unPatientMedecin)throws DAOException;
	
	
}
