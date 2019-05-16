package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Duree;
import fr.medoc.exception.DAOException;


public interface DureeDAO {

	public void ajouterDuree (Duree uneDuree)throws DAOException;
	public void supprimerDuree(int idDuree)throws DAOException;
	public Collection<Duree> findAll() throws DAOException;	
	public Duree findByRef (int id)throws DAOException;
	public Duree findByName (String nom)throws DAOException;
}
