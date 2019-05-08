package fr.medoc.dao;

import java.util.Collection;

import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOException;


public interface UtilisateurDAO {

	public void ajouterUtilisateur (Utilisateur unUtilisateur)throws DAOException;
	public void supprimerUtilisateur(int idUtilisateur)throws DAOException;
	public Collection<Utilisateur> findAll() throws DAOException;	
	public Utilisateur findByRef (int id)throws DAOException;
	public Utilisateur findByName (String nom)throws DAOException;
}
