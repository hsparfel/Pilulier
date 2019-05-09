package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Specialite;
import fr.medoc.exception.DAOException;


public interface SpecialiteDAO {

	public void ajouterSpecialite (Specialite uneSpecialite)throws DAOException;
	public void supprimerSpecialite(int idSpecialite)throws DAOException;
	public Collection<Specialite> findAll() throws DAOException;	
	public Specialite findByRef (int id)throws DAOException;
	public Specialite findByName (String nom)throws DAOException;
}
