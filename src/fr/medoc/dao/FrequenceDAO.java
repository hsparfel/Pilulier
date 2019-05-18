package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Frequence;
import fr.medoc.exception.DAOException;


public interface FrequenceDAO {

	public void ajouterFrequence (Frequence uneFrequence)throws DAOException;
	public void supprimerFrequence(int idFrequence)throws DAOException;
	public Collection<Frequence> findAll() throws DAOException;	
	public Frequence findByRef (int id)throws DAOException;
	public Frequence findByName (String nom)throws DAOException;
	public void modifierFrequence(Frequence frequence, int id) throws DAOException;
}
