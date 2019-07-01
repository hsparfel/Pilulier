package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Examen;
import fr.medoc.exception.DAOException;


public interface ExamenDAO {

	public void ajouterExamen (Examen uneExamen)throws DAOException;
	public void supprimerExamen(int idExamen)throws DAOException;
	public Collection<Examen> findAll() throws DAOException;	
	public Examen findByRef (int id)throws DAOException;
	public void modifierExamen(Examen examen, int id) throws DAOException;
}
