package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Cabinet;
import fr.medoc.exception.DAOException;


public interface CabinetDAO {

	public void ajouterCabinet (Cabinet unCabinet)throws DAOException;
	public void supprimerCabinet(int idCabinet)throws DAOException;
	public Collection<Cabinet> findAll() throws DAOException;	
	public Cabinet findByRef (int id)throws DAOException;
	public Cabinet findByName (String nom)throws DAOException;
	public void modifierCabinet(Cabinet cabinet, int id) throws DAOException;
}
