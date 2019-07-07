package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Profil;
import fr.medoc.exception.DAOException;


public interface ProfilDAO {

	public void ajouterProfil (Profil unProfil)throws DAOException;
	public void supprimerProfil(int idProfil)throws DAOException;
	public Collection<Profil> findAllByUser(int id) throws DAOException;	
	public Profil findByRef (int id)throws DAOException;
	public void modifierProfil(Profil dose, int id) throws DAOException;
}
