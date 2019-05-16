package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Medicament;
import fr.medoc.exception.DAOException;


public interface MedicamentDAO {

	public void ajouterMedicament (Medicament unMedicament)throws DAOException;
	public void supprimerMedicament(int idMedicament)throws DAOException;
	public Collection<Medicament> findAll() throws DAOException;	
	public Medicament findByRef (int id)throws DAOException;
	public Medicament findByName (String nom)throws DAOException;

	public Collection<Medicament> findAllByUser(int id) throws DAOException;
	public Collection<Medicament> findAllExcludedByUser(String unUtilisateur) throws DAOException;
}
