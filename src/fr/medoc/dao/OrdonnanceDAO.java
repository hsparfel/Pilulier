package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Ordonnance;
import fr.medoc.exception.DAOException;


public interface OrdonnanceDAO {

	public void ajouterOrdonnance (Ordonnance uneOrdonnance)throws DAOException;
	public Collection<Ordonnance> findAllByUser(int id) throws DAOException;
	public void supprimerOrdonnance(int idOrdonnance)throws DAOException;
	public void modifierOrdonnance(Ordonnance ordonnance, int id) throws DAOException;
	public Ordonnance findByRef (int id)throws DAOException;
}
