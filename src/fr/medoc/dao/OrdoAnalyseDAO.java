package fr.medoc.dao;

import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.entities.OrdoAnalyse;
import fr.medoc.entities.OrdoPrescription;
import fr.medoc.exception.DAOException;


public interface OrdoAnalyseDAO {

	public void ajouterAnalyse (OrdoAnalyse uneAnalyse)throws DAOException;
	public void supprimerAnalyse(int idAnalyse)throws DAOException;
	public Collection<OrdoAnalyse> findAll() throws DAOException;	
	public OrdoAnalyse findByRef (int id)throws DAOException;
	public void modifierAnalyse(OrdoAnalyse ordoAnalyse, int id) throws DAOException;
	public Collection<OrdoAnalyse> findAllByUser(int id) throws DAOException;
	public Collection<OrdoAnalyse> findByOrdonnance(int id) throws DAOException;
	public void ajouterNewAnalyse(OrdoAnalyse uneAnalyse) throws DAOException;
}
