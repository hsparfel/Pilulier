package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.MedecinDAO;
import fr.medoc.dao.SpecialiteDAO;
import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.DoseDAO;
import fr.medoc.entities.Medecin;
import fr.medoc.entities.Medicament;
import fr.medoc.entities.Prescription;
import fr.medoc.exception.DAOException;


public class MedecinDAOImpl implements MedecinDAO{
	
	private ArrayList<Medecin> listeMedecins;
	private ArrayList<Medecin> listeMedecinsTries;
	private ArrayList<Medecin> listeMedecinsExclus;
	
	private final String ORDRE_INSERT = "insert into medecin(Nom,id_specialite,id_cabinet,telephone, email) values ";
	private final String VALUES_INSERT = "(?,?,?,?,?)";
	private final String ORDRE_DELETE = "delete from medecin where Id = ";
	private final String ORDRE_FINDALL = "select * from medecin";
	private final String ORDRE_FINDBYREF = "select * from medecin where Id = ?";
	private final String ORDRE_FINDBYNAME = "select * from medecin where Nom = ?";
	private final String ORDRE_FINDALLBYUSER = "select * from utilisateur_medecin AS um where um.id_utilisateur=?";
	private final String ORDRE_FINDALLFILTERED = "select * from medecin AS m where m.nom NOT IN (select m.nom from medecin AS m, utilisateur AS u, utilisateur_medecin AS um where m.id=um.id_medecin AND u.id=um.id_utilisateur AND u.nom=?)";
	
    private DAOFactory daoFactory;

	public MedecinDAOImpl(DAOFactory daoFactory) {
		listeMedecins = new ArrayList<Medecin>();
		listeMedecinsTries = new ArrayList<Medecin>();
		listeMedecinsExclus = new ArrayList<Medecin>();
		this.daoFactory = daoFactory;
	}
	@Override
	public void ajouterMedecin(Medecin unMedecin) throws DAOException{
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListeMedecins().add(unMedecin);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, unMedecin.getNom());
			pst.setInt(2, unMedecin.getSpecialite().getId());
			pst.setInt(3, unMedecin.getCabinet().getId());
			pst.setString(4, unMedecin.getTelephone());
			pst.setString(5, unMedecin.getEmail());
			
			
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()){
				unMedecin.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un medecin. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public void supprimerMedecin(int idMedecin)throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'"+idMedecin+"'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public Medecin findByRef (int id) throws DAOException{
		Medecin unMedecin = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unMedecin = new Medecin();	
				SpecialiteDAO uneSpecialiteDAO = daoFactory.getSpecialiteDAO();
				CabinetDAO unCabinetDAO = daoFactory.getCabinetDAO();
				
				
				unMedecin.setId(rs.getInt("id"));
				unMedecin.setNom(rs.getString("nom"));
				unMedecin.setSpecialite(uneSpecialiteDAO.findByRef(rs.getInt("id_specialite")));
				unMedecin.setCabinet(unCabinetDAO.findByRef(rs.getInt("id_cabinet")));
				unMedecin.setTelephone(rs.getString("telephone"));
				unMedecin.setEmail(rs.getString("email"));
			} else {
				throw new DAOException("Erreur recherche d'un medecin. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unMedecin; 
	}
	@Override
	public Medecin findByName (String nom) throws DAOException{
		Medecin unMedecin = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYNAME);
			pst.setString(1, nom);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				
				unMedecin = new Medecin();	
				SpecialiteDAO uneSpecialiteDAO = daoFactory.getSpecialiteDAO();
				CabinetDAO unCabinetDAO = daoFactory.getCabinetDAO();
				
				
				unMedecin.setId(rs.getInt("id"));
				unMedecin.setNom(rs.getString("nom"));
				unMedecin.setSpecialite(uneSpecialiteDAO.findByRef(rs.getInt("id_specialite")));
				unMedecin.setCabinet(unCabinetDAO.findByRef(rs.getInt("id_cabinet")));
				unMedecin.setTelephone(rs.getString("telephone"));
				unMedecin.setEmail(rs.getString("email"));
				
				
				
				
				
				
				
				
			} else {
				throw new DAOException("Erreur recherche d'un medecin. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unMedecin; 
	}
	@Override
	public Collection<Medecin> findAll() throws DAOException {	
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeMedecins.removeAll(listeMedecins);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeMedecins;
	}
	
	@Override
	public Collection<Medecin> findAllByUser(int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLBYUSER);
			pst.setInt(1, id);
			ResultSet resultSet = pst.executeQuery();
			listeMedecinsTries.removeAll(listeMedecinsTries);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeMedecinsTries;
	}
	
	@Override
	public Collection<Medecin> findAllExcludedByUser(String unUtilisateur) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLFILTERED);
			pst.setString(1, unUtilisateur);
			ResultSet resultSet = pst.executeQuery();
			listeMedecinsExclus.removeAll(listeMedecinsExclus);
			resultSetToArrayListExcluded(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeMedecinsExclus;
	}
	
	
	
	public void setListeMedecins(ArrayList<Medecin> listeMedecins) {
		this.listeMedecins = listeMedecins;
	}

	public Collection<Medecin> getListeMedecins() {
		return listeMedecins;
	}
	
	public ArrayList<Medecin> getListeMedecinsTries() {
		return listeMedecinsTries;
	}
	public void setListeMedecinsTries(ArrayList<Medecin> listeMedecinsTries) {
		this.listeMedecinsTries = listeMedecinsTries;
	}
	public ArrayList<Medecin> getListeMedecinsExclus() {
		return listeMedecinsExclus;
	}
	public void setListeMedecinsExclus(ArrayList<Medecin> listeMedecinsExclus) {
		this.listeMedecinsExclus = listeMedecinsExclus;
	}
	private void resultSetToArrayList(ResultSet resultSet)
			throws SQLException, DAOException {

		while (resultSet.next()) {
			Medecin a = new Medecin();
			SpecialiteDAO uneSpecialiteDAO = daoFactory.getSpecialiteDAO();
			CabinetDAO unCabinetDAO = daoFactory.getCabinetDAO();
			
			a.setId(resultSet.getInt("id"));
			a.setNom(resultSet.getString("nom"));
			a.setSpecialite(uneSpecialiteDAO.findByRef(resultSet.getInt("id_specialite")));
			a.setCabinet(unCabinetDAO.findByRef(resultSet.getInt("id_cabinet")));
			a.setTelephone(resultSet.getString("telephone"));
			a.setEmail(resultSet.getString("email"));
			
			
			getListeMedecins().add(a);
		}
	}
	
	private void resultSetToArrayListFiltered(ResultSet resultSet)
			throws SQLException, DAOException {

		while (resultSet.next()) {
			SpecialiteDAO uneSpecialiteDAO = daoFactory.getSpecialiteDAO();
			Medecin a = new Medecin();
			a.setId(resultSet.getInt("id"));
			a.setNom(resultSet.getString("nom"));
			a.setSpecialite(uneSpecialiteDAO.findByRef(resultSet.getInt("id_specialite")));
			getListeMedecinsExclus().add(a);
		}
	}

	private void resultSetToArrayListExcluded(ResultSet resultSet)
			throws SQLException, DAOException {

		while (resultSet.next()) {
			SpecialiteDAO uneSpecialiteDAO = daoFactory.getSpecialiteDAO();
			Medecin a = new Medecin();
			a.setId(resultSet.getInt("id"));
			a.setNom(resultSet.getString("nom"));
			a.setSpecialite(uneSpecialiteDAO.findByRef(resultSet.getInt("id_specialite")));
			getListeMedecinsExclus().add(a);
		}
	}
}
