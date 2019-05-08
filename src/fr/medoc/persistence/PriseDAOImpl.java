package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.PriseDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.entities.Prise;
import fr.medoc.entities.Prescription;
import fr.medoc.exception.DAOException;


public class PriseDAOImpl implements PriseDAO{
	
	private ArrayList<Prise> listePrises;
		
	private final String ORDRE_INSERT = "insert into prise(id_utiliateur, id_medicament, date) values ";
	private final String VALUES_INSERT = "(?,?,?)";
	private final String ORDRE_DELETE = "delete from prise where Id = ";
	private final String ORDRE_FINDALL = "select * from prise";
	private final String ORDRE_FINDBYREF = "select * from prise where Id = ?";
	private final String ORDRE_FINDALLLASTBYUSER = "select  p.id, p.id_utilisateur, p.id_medicament, p.date from prise AS p where id_utilisateur = ? AND date = (SELECT MAX(p2.date) FROM prise AS p2 WHERE p2.id_utilisateur=p.id_utilisateur AND p2.id_medicament=p.id_medicament) order by id_medicament, date DESC";
	
	
	
    private DAOFactory          daoFactory;

	public PriseDAOImpl(DAOFactory daoFactory) {
		listePrises = new ArrayList<Prise>();
		this.daoFactory = daoFactory;
	}
	@Override
	public void ajouterPrise(Prise unePrise) throws DAOException{
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListePrises().add(unePrise);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, unePrise.getUtilisateur().getId());
			pst.setInt(2, unePrise.getMedicament().getId());
			pst.setString(3, unePrise.getDatePrise());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()){
				unePrise.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'une prise. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public void supprimerPrise(int idPrise)throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'"+idPrise+"'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public Prise findByRef (int id) throws DAOException{
		Prise unePrise = null;
		Connection connexion = null;
		UtilisateurDAO unUtilisateurDAO = daoFactory.getUtilisateurDAO();
		MedicamentDAO unMedicamentDAO = daoFactory.getMedicamentDAO();
		
		
		
		
		
		
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unePrise = new Prise(unUtilisateurDAO.findByRef(rs.getInt("id_utilisateur")),unMedicamentDAO.findByRef(rs.getInt("id_medicament")), rs.getString("date"));	
				unePrise.setId(id);
			} else {
				throw new DAOException("Erreur recherche d'une prise. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unePrise; 
	}
	
	@Override
	public Collection<Prise> findAll() throws DAOException {	
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listePrises.removeAll(listePrises);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listePrises;
	}
	
	public void setListePrises(ArrayList<Prise> listePrises) {
		this.listePrises = listePrises;
	}

	public Collection<Prise> getListePrises() {
		return listePrises;
	}
	
	
	
	private void resultSetToArrayList(ResultSet resultSet)
			throws SQLException, DAOException {

		while (resultSet.next()) {
			Prise a = new Prise();
			UtilisateurDAO unUtilisateurDAO = daoFactory.getUtilisateurDAO();
			MedicamentDAO unMedicamentDAO = daoFactory.getMedicamentDAO();
			
			
			
			
			a.setId(resultSet.getInt("id"));
			a.setUtilisateur(unUtilisateurDAO.findByRef(resultSet.getInt("id_utilisateur")));
			a.setMedicament(unMedicamentDAO.findByRef(resultSet.getInt("id_medicament")));
			a.setDatePrise(resultSet.getString("date"));
			
			getListePrises().add(a);
		}
	}
	
	@Override
	public Collection<Prise> findAllLastByUser(int id_utilisateur) throws DAOException {

		Prise unePrise = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLLASTBYUSER);
			pst.setInt(1, id_utilisateur);
			
			ResultSet rs = pst.executeQuery();
			listePrises.removeAll(listePrises);
			resultSetToArrayList(rs);
			
			
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listePrises; 
	}
	
	

	
}
