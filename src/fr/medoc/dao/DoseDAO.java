package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Dose;
import fr.medoc.exception.DAOException;


public interface DoseDAO {

	public void ajouterDose (Dose uneDose)throws DAOException;
	public void supprimerDose(int idDose)throws DAOException;
	public Collection<Dose> findAll() throws DAOException;	
	public Dose findByRef (int id)throws DAOException;
	public Dose findByName (String nom)throws DAOException;
	public void modifierDose(Dose dose, int id) throws DAOException;
}
