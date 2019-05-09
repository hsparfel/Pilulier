package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.SpecialiteDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Specialite;
import fr.medoc.exception.DAOException;


public class SpecialiteDAOImpl implements SpecialiteDAO{
	
	private ArrayList<Specialite> listeSpecialites;
	private final String ORDRE_INSERT = "insert into specialite(Nom) values ";
	private final String VALUES_INSERT = "(?)";
	private final String ORDRE_DELETE = "delete from specialite where Id = ";
	private final String ORDRE_FINDALL = "select Id,Nom from specialite";
	private final String ORDRE_FINDBYREF = "select Id,Nom from specialite where Id = ?";
	private final String ORDRE_FINDBYNAME = "select Id,Nom from specialite where Nom = ?";
	
    private DAOFactory daoFactory;

	public SpecialiteDAOImpl(DAOFactory daoFactory) {
		listeSpecialites = new ArrayList<Specialite>();
		this.daoFactory = daoFactory;
	}
	@Override
	public void ajouterSpecialite(Specialite uneSpecialite) throws DAOException{
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListeSpecialites().add(uneSpecialite);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, uneSpecialite.getNom());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()){
				uneSpecialite.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un specialite. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public void supprimerSpecialite(int idSpecialite)throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'"+idSpecialite+"'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public Specialite findByRef (int id) throws DAOException{
		Specialite uneSpecialite = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneSpecialite = new Specialite( rs.getString("nom"));	
				uneSpecialite.setId(id);
			} else {
				throw new DAOException("Erreur recherche d'un specialite. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneSpecialite; 
	}
	@Override
	public Specialite findByName (String nom) throws DAOException{
		Specialite uneSpecialite = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYNAME);
			pst.setString(1, nom);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneSpecialite = new Specialite( rs.getString("nom"));	
				uneSpecialite.setId(rs.getInt("id"));
			} else {
				throw new DAOException("Erreur recherche d'un specialite. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneSpecialite; 
	}
	@Override
	public Collection<Specialite> findAll() throws DAOException {	
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeSpecialites.removeAll(listeSpecialites);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeSpecialites;
	}
	
	public void setListeSpecialites(ArrayList<Specialite> listeSpecialites) {
		this.listeSpecialites = listeSpecialites;
	}

	public Collection<Specialite> getListeSpecialites() {
		return listeSpecialites;
	}
	
	private void resultSetToArrayList(ResultSet resultSet)
			throws SQLException {

		while (resultSet.next()) {
			Specialite a = new Specialite();
			a.setId(resultSet.getInt("id"));
			a.setNom(resultSet.getString("nom"));
			getListeSpecialites().add(a);
		}
	}

}
