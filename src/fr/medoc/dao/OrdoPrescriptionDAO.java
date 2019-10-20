package fr.medoc.dao;

import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.entities.OrdoPrescription;
import fr.medoc.exception.DAOException;


public interface OrdoPrescriptionDAO {

	public void ajouterPrescription (OrdoPrescription unePrescription)throws DAOException;
	public Collection<OrdoPrescription> findAllByUser(int id) throws DAOException;
	public void supprimerPrescription(int idPrescription)throws DAOException;
	public void modifierPrescription(OrdoPrescription prescription, int id) throws DAOException;
	public OrdoPrescription findByRef (int id)throws DAOException;
	public Collection<OrdoPrescription> findByOrdonnance(int id) throws DAOException;
}
