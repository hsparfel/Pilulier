package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Dose;
import fr.medoc.entities.Frequence;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Prescription;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOException;


public interface PrescriptionDAO {

	public void ajouterPrescription (Prescription unePrescription)throws DAOException;
	public Collection<Prescription> findAllByUser(int id) throws DAOException;
	public Prescription findByRefs (int idUtilisateur, int idMedicament)throws DAOException;
	
	
}
