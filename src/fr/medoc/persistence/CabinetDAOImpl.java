package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Cabinet;
import fr.medoc.exception.DAOException;


public class CabinetDAOImpl implements CabinetDAO{
	
	private ArrayList<Cabinet> listeCabinets;
	private final String ORDRE_INSERT = "insert into cabinet(Nom,adresse,cp,ville) values ";
	private final String VALUES_INSERT = "(?,?,?,?)";
	private final String ORDRE_DELETE = "delete from cabinet where Id = ";
	private final String ORDRE_FINDALL = "select * from cabinet";
	private final String ORDRE_FINDBYREF = "select Id,Nom,adresse,cp,ville from cabinet where Id = ?";
	private final String ORDRE_FINDBYNAME = "select Id,Nom,adresse,cp,ville from cabinet where Nom = ?";
	
    private DAOFactory daoFactory;

	public CabinetDAOImpl(DAOFactory daoFactory) {
		listeCabinets = new ArrayList<Cabinet>();
		this.daoFactory = daoFactory;
	}
	@Override
	public void ajouterCabinet(Cabinet unCabinet) throws DAOException{
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListeCabinets().add(unCabinet);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, unCabinet.getNom());
			pst.setString(2, unCabinet.getAdresse());
			pst.setString(3, unCabinet.getCp());
			pst.setString(4, unCabinet.getVille());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()){
				unCabinet.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un cabinet. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public void supprimerCabinet(int idCabinet)throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'"+idCabinet+"'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public Cabinet findByRef (int id) throws DAOException{
		Cabinet unCabinet = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unCabinet = new Cabinet( rs.getString("nom"),rs.getString("adresse"),rs.getString("cp"),rs.getString("ville"));	
				unCabinet.setId(id);
			} else {
				throw new DAOException("Erreur recherche d'un cabinet. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unCabinet; 
	}
	@Override
	public Cabinet findByName (String nom) throws DAOException{
		Cabinet unCabinet = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYNAME);
			pst.setString(1, nom);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unCabinet = new Cabinet( rs.getString("nom"),rs.getString("adresse"),rs.getString("cp"),rs.getString("ville"));	
				unCabinet.setId(rs.getInt("id"));
			} else {
				throw new DAOException("Erreur recherche d'un cabinet. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unCabinet; 
	}
	@Override
	public Collection<Cabinet> findAll() throws DAOException {	
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeCabinets.removeAll(listeCabinets);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeCabinets;
	}
	
	public void setListeCabinets(ArrayList<Cabinet> listeCabinets) {
		this.listeCabinets = listeCabinets;
	}

	public Collection<Cabinet> getListeCabinets() {
		return listeCabinets;
	}
	
	private void resultSetToArrayList(ResultSet resultSet)
			throws SQLException {

		while (resultSet.next()) {
			Cabinet a = new Cabinet();
			a.setId(resultSet.getInt("id"));
			a.setNom(resultSet.getString("nom"));
			a.setAdresse(resultSet.getString("adresse"));
			a.setCp(resultSet.getString("cp"));
			a.setVille(resultSet.getString("ville"));
			getListeCabinets().add(a);
		}
	}

}
