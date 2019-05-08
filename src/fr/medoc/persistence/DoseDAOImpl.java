package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Dose;
import fr.medoc.exception.DAOException;


public class DoseDAOImpl implements DoseDAO{
	
	private ArrayList<Dose> listeDoses;
	private final String ORDRE_INSERT = "insert into dose(Nom) values ";
	private final String VALUES_INSERT = "(?)";
	private final String ORDRE_DELETE = "delete from dose where Id = ";
	private final String ORDRE_FINDALL = "select Id,Nom from dose";
	private final String ORDRE_FINDBYREF = "select Id,Nom from dose where Id = ?";
	private final String ORDRE_FINDBYNAME = "select Id,Nom from dose where Nom = ?";
	
    private DAOFactory daoFactory;

	public DoseDAOImpl(DAOFactory daoFactory) {
		listeDoses = new ArrayList<Dose>();
		this.daoFactory = daoFactory;
	}
	@Override
	public void ajouterDose(Dose uneDose) throws DAOException{
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListeDoses().add(uneDose);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, uneDose.getNom());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()){
				uneDose.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un dose. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public void supprimerDose(int idDose)throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'"+idDose+"'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public Dose findByRef (int id) throws DAOException{
		Dose uneDose = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneDose = new Dose( rs.getString("nom"));	
				uneDose.setId(id);
			} else {
				throw new DAOException("Erreur recherche d'un dose. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneDose; 
	}
	@Override
	public Dose findByName (String nom) throws DAOException{
		Dose uneDose = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYNAME);
			pst.setString(1, nom);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneDose = new Dose( rs.getString("nom"));	
				uneDose.setId(rs.getInt("id"));
			} else {
				throw new DAOException("Erreur recherche d'un dose. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneDose; 
	}
	@Override
	public Collection<Dose> findAll() throws DAOException {	
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeDoses.removeAll(listeDoses);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeDoses;
	}
	
	public void setListeDoses(ArrayList<Dose> listeDoses) {
		this.listeDoses = listeDoses;
	}

	public Collection<Dose> getListeDoses() {
		return listeDoses;
	}
	
	private void resultSetToArrayList(ResultSet resultSet)
			throws SQLException {

		while (resultSet.next()) {
			Dose a = new Dose();
			a.setId(resultSet.getInt("id"));
			a.setNom(resultSet.getString("nom"));
			getListeDoses().add(a);
		}
	}

}
