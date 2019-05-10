package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Medecin;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Prescription;
import fr.medoc.exception.DAOException;


public interface MedecinDAO {

	public void ajouterMedecin (Medecin unMedecin)throws DAOException;
	public void supprimerMedecin(int idMedecin)throws DAOException;
	public Collection<Medecin> findAll() throws DAOException;	
	public Medecin findByRef (int id)throws DAOException;
	public Medecin findByName (String nom)throws DAOException;
	public Collection<Medecin> findAllByUser(int id) throws DAOException;
	public Collection<Medecin> findAllExcludedByUser(String unUtilisateur) throws DAOException;
}
