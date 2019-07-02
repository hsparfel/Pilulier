package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Prescription2;
import fr.medoc.exception.DAOException;


public interface Prescription2DAO {

	public void ajouterPrescription (Prescription2 unePrescription)throws DAOException;
	public Collection<Prescription2> findAllByUser(int id) throws DAOException;
	public void supprimerPrescription(int idPrescription)throws DAOException;
	public void modifierPrescription(Prescription2 prescription, int id) throws DAOException;
	public Prescription2 findByRef (int id)throws DAOException;
}
