package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Analyse;
import fr.medoc.entities.Prescription2;
import fr.medoc.exception.DAOException;


public interface AnalyseDAO {

	public void ajouterAnalyse (Analyse uneAnalyse)throws DAOException;
	public void supprimerAnalyse(int idAnalyse)throws DAOException;
	public Collection<Analyse> findAll() throws DAOException;	
	public Analyse findByRef (int id)throws DAOException;
	public void modifierAnalyse(Analyse analyse, int id) throws DAOException;
	public Collection<Analyse> findAllByUser(int id) throws DAOException;
}
