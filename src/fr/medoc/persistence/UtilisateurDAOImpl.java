package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Utilisateur;
import fr.medoc.exception.DAOException;


public class UtilisateurDAOImpl implements UtilisateurDAO{
	
	private ArrayList<Utilisateur> listeUtilisateurs;
	private final String ORDRE_INSERT = "insert into utilisateur(Nom) values ";
	private final String VALUES_INSERT = "(?)";
	private final String ORDRE_DELETE = "delete from utilisateur where Id = ";
	private final String ORDRE_FINDALL = "select Id,Nom from utilisateur";
	private final String ORDRE_FINDBYREF = "select Id,Nom from utilisateur where Id = ?";
	private final String ORDRE_FINDBYNAME = "select Id,Nom from utilisateur where Nom = ?";
	
    private DAOFactory          daoFactory;

	public UtilisateurDAOImpl(DAOFactory daoFactory) {
		listeUtilisateurs = new ArrayList<Utilisateur>();
		this.daoFactory = daoFactory;
	}
	@Override
	public void ajouterUtilisateur(Utilisateur unUtilisateur) throws DAOException{
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListeUtilisateurs().add(unUtilisateur);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, unUtilisateur.getNom());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()){
				unUtilisateur.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un utilisateur. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public void supprimerUtilisateur(int idUtilisateur)throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'"+idUtilisateur+"'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public Utilisateur findByRef (int id) throws DAOException{
		Utilisateur unUtilisateur = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unUtilisateur = new Utilisateur( rs.getString("nom"));	
				unUtilisateur.setId(id);
			} else {
				throw new DAOException("Erreur recherche d'un utilisateur. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unUtilisateur; 
	}
	@Override
	public Utilisateur findByName (String nom) throws DAOException{
		Utilisateur unUtilisateur = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYNAME);
			pst.setString(1, nom);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unUtilisateur = new Utilisateur( rs.getString("nom"));	
				unUtilisateur.setId(rs.getInt("id"));
			} else {
				throw new DAOException("Erreur recherche d'un utilisateur. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unUtilisateur; 
	}
	@Override
	public Collection<Utilisateur> findAll() throws DAOException {	
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeUtilisateurs.removeAll(listeUtilisateurs);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeUtilisateurs;
	}
	
	public void setListeUtilisateurs(ArrayList<Utilisateur> listeUtilisateurs) {
		this.listeUtilisateurs = listeUtilisateurs;
	}

	public Collection<Utilisateur> getListeUtilisateurs() {
		return listeUtilisateurs;
	}
	
	private void resultSetToArrayList(ResultSet resultSet)
			throws SQLException {

		while (resultSet.next()) {
			Utilisateur a = new Utilisateur();
			a.setId(resultSet.getInt("id"));
			a.setNom(resultSet.getString("nom"));
			getListeUtilisateurs().add(a);
		}
	}

}
