package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Prise;
import fr.medoc.exception.DAOException;


public interface PriseDAO {

	public void ajouterPrise (Prise unePrise)throws DAOException;
	public void supprimerPrise(int idPrise)throws DAOException;
	public Collection<Prise> findAll() throws DAOException;	
	public Prise findByRef (int id)throws DAOException;
	}
