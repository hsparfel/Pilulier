package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Prise;
import fr.medoc.exception.DAOException;


public interface PriseDAO {

	public void ajouterPrise (Prise unePrise)throws DAOException;
	public void supprimerPrise(int idPrise)throws DAOException;
	public Collection<Prise> findAll() throws DAOException;
	public Collection<Prise> findAllByUser(int id) throws DAOException;
	public Prise findByRef (int id)throws DAOException;
	public void supprimerPrisesByPrescription(int idPrescription)throws DAOException;
	public void validerPrise (int idPrise)throws DAOException;
	}
