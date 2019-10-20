package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.OrdoAnalyse;
import fr.medoc.entities.OrdoExamen;
import fr.medoc.exception.DAOException;


public interface OrdoExamenDAO {

	public void ajouterExamen (OrdoExamen uneExamen)throws DAOException;
	public void supprimerExamen(int idExamen)throws DAOException;
	public Collection<OrdoExamen> findAll() throws DAOException;	
	public OrdoExamen findByRef (int id)throws DAOException;
	public void modifierExamen(OrdoExamen ordoExamen, int id) throws DAOException;
	public Collection<OrdoExamen> findAllByUser(int id) throws DAOException;
	public Collection<OrdoExamen> findByOrdonnance(int id) throws DAOException;
	public void ajouterNewExamen(OrdoExamen unExamen) throws DAOException;
}
