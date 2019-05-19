package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.PatientMedecin;
import fr.medoc.entities.Rdv;
import fr.medoc.exception.DAOException;


public interface RdvDAO {

	public void ajouterRdv (Rdv unRdv)throws DAOException;
	public void supprimerRdv(int idRdv)throws DAOException;
	public void supprimerRdvByUserAndMedecin(PatientMedecin patientMedecin)throws DAOException;
	public Collection<Rdv> findAll() throws DAOException;	
	public Rdv findByRef (int id)throws DAOException;
	public Collection<Rdv> findAllByUser(int id) throws DAOException;
	public void modifierRdv(Rdv rdv, int id) throws DAOException;
}
