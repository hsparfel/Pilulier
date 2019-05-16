package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.DureeDAO;
import fr.medoc.dao.DureeDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Duree;
import fr.medoc.exception.DAOException;


public class DureeDAOImpl implements DureeDAO{
	
	private ArrayList<Duree> listeDurees;
	private final String ORDRE_INSERT = "insert into duree(Nom) values ";
	private final String VALUES_INSERT = "(?)";
	private final String ORDRE_DELETE = "delete from duree where Id = ";
	private final String ORDRE_FINDALL = "select Id,Nom from duree";
	private final String ORDRE_FINDBYREF = "select Id,Nom from duree where Id = ?";
	private final String ORDRE_FINDBYNAME = "select Id,Nom from duree where Nom = ?";
	
    private DAOFactory daoFactory;

	public DureeDAOImpl(DAOFactory daoFactory) {
		listeDurees = new ArrayList<Duree>();
		this.daoFactory = daoFactory;
	}
	@Override
	public void ajouterDuree(Duree uneDuree) throws DAOException{
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListeDurees().add(uneDuree);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, uneDuree.getNom());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()){
				uneDuree.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un duree. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public void supprimerDuree(int idDuree)throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'"+idDuree+"'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public Duree findByRef (int id) throws DAOException{
		Duree uneDuree = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneDuree = new Duree( rs.getString("nom"));	
				uneDuree.setId(id);
			} else {
				throw new DAOException("Erreur recherche d'un duree. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneDuree; 
	}
	@Override
	public Duree findByName (String nom) throws DAOException{
		Duree uneDuree = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYNAME);
			pst.setString(1, nom);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneDuree = new Duree( rs.getString("nom"));	
				uneDuree.setId(rs.getInt("id"));
			} else {
				throw new DAOException("Erreur recherche d'un duree. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneDuree; 
	}
	@Override
	public Collection<Duree> findAll() throws DAOException {	
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeDurees.removeAll(listeDurees);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeDurees;
	}
	
	public void setListeDurees(ArrayList<Duree> listeDurees) {
		this.listeDurees = listeDurees;
	}

	public Collection<Duree> getListeDurees() {
		return listeDurees;
	}
	
	private void resultSetToArrayList(ResultSet resultSet)
			throws SQLException {

		while (resultSet.next()) {
			Duree a = new Duree();
			a.setId(resultSet.getInt("id"));
			a.setNom(resultSet.getString("nom"));
			getListeDurees().add(a);
		}
	}

}
