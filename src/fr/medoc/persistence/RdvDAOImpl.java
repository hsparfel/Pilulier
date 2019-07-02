package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.RdvDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.entities.PatientMedecin;
import fr.medoc.entities.Rdv;
import fr.medoc.exception.DAOException;


public class RdvDAOImpl implements RdvDAO{
	
	private ArrayList<Rdv> listeRdvs;
	private ArrayList<Rdv> listeRdvsTries;
	
	
	private final String ORDRE_INSERT = "insert into rdv(id_utilisateur, id_medecin, date, heure, commentaire) values ";
	private final String VALUES_INSERT = "(?,?,?,?,?)";
	private final String ORDRE_DELETE = "delete from rdv where Id = ";
	private final String ORDRE_DELETEBYUSERANDMEDECIN = "delete from rdv where id_utilisateur = ? AND id_medecin=?";
	private final String ORDRE_FINDALL = "select * from rdv";
	private final String ORDRE_FINDBYREF = "select * from rdv where Id = ?";
	private final String ORDRE_FINDALLBYUSER = "select * from rdv where id_utilisateur=? order by (SUBSTRING(date,7,4)), (SUBSTRING(date,4,2)), (SUBSTRING(date,1,2))";
	private final String ORDRE_UPDATE = "update rdv set id_utilisateur=?,id_medecin=?,date=?,heure=?,commentaire=? where id = ?";
    private DAOFactory daoFactory;

	public RdvDAOImpl(DAOFactory daoFactory) {
		listeRdvs = new ArrayList<Rdv>();
		listeRdvsTries = new ArrayList<Rdv>();
		
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void modifierRdv(Rdv uneRdv, int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeRdvs().add(uneRdv);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_UPDATE);
			pst.setInt(1, uneRdv.getUtilisateur().getId());
			pst.setInt(2, uneRdv.getMedecin().getId());
			pst.setString(3, uneRdv.getDate());
			pst.setString(4, uneRdv.getHeure());
			pst.setString(5, uneRdv.getCommentaire());
			pst.setInt(6, id);
			pst.executeUpdate();
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	public void supprimerRdvByUserAndMedecin(PatientMedecin patientMedecin)throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_DELETEBYUSERANDMEDECIN);
			pst.setInt(1, patientMedecin.getPatient().getId());
			pst.setInt(2, patientMedecin.getMedecin().getId());
			pst.executeUpdate();
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	public void ajouterRdv(Rdv unRdv) throws DAOException{
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListeRdvs().add(unRdv);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, unRdv.getUtilisateur().getId());
			pst.setInt(2, unRdv.getMedecin().getId());
			pst.setString(3, unRdv.getDate());
			pst.setString(4, unRdv.getHeure());
			pst.setString(5, unRdv.getCommentaire());
			
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()){
				unRdv.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un rdv. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public void supprimerRdv(int idRdv)throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'"+idRdv+"'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	@Override
	public Rdv findByRef (int id) throws DAOException{
		Rdv unRdv = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unRdv = new Rdv();	
				UtilisateurDAO unUtilisateurDAO = daoFactory.getUtilisateurDAO();
				MedecinDAO unMedecinDAO = daoFactory.getMedecinDAO();
				unRdv.setId(rs.getInt("id"));
				unRdv.setUtilisateur(unUtilisateurDAO.findByRef(rs.getInt("id_utilisateur")));
				unRdv.setMedecin(unMedecinDAO.findByRef(rs.getInt("id_medecin")));
				unRdv.setDate(rs.getString("date"));
				unRdv.setHeure(rs.getString("heure"));
				unRdv.setCommentaire(rs.getString("commentaire"));
			} else {
				throw new DAOException("Erreur recherche d'un rdv. " );
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unRdv; 
	}
	
	@Override
	public Collection<Rdv> findAll() throws DAOException {	
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeRdvs.removeAll(listeRdvs);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeRdvs;
	}
	
	@Override
	public Collection<Rdv> findAllByUser(int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLBYUSER);
			pst.setInt(1, id);
			ResultSet resultSet = pst.executeQuery();
			listeRdvsTries.removeAll(listeRdvsTries);
			resultSetToArrayListFiltered(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeRdvsTries;
	}
	
	
	
	
	
	public void setListeRdvs(ArrayList<Rdv> listeRdvs) {
		this.listeRdvs = listeRdvs;
	}

	public Collection<Rdv> getListeRdvs() {
		return listeRdvs;
	}
	
	public ArrayList<Rdv> getListeRdvsTries() {
		return listeRdvsTries;
	}
	public void setListeRdvsTries(ArrayList<Rdv> listeRdvsTries) {
		this.listeRdvsTries = listeRdvsTries;
	}
	
		private void resultSetToArrayList(ResultSet resultSet)
			throws SQLException, DAOException {

		while (resultSet.next()) {
			Rdv a = new Rdv();
			UtilisateurDAO unUtilisateurDAO = daoFactory.getUtilisateurDAO();
			MedecinDAO unMedecinDAO = daoFactory.getMedecinDAO();
			
			a.setId(resultSet.getInt("id"));
			a.setUtilisateur(unUtilisateurDAO.findByRef(resultSet.getInt("id_utilisateur")));
			a.setMedecin(unMedecinDAO.findByRef(resultSet.getInt("id_medecin")));
			a.setDate(resultSet.getString("date"));
			a.setHeure(resultSet.getString("heure"));
			a.setCommentaire(resultSet.getString("commentaire"));		
			getListeRdvs().add(a);
		}
	}
	
	private void resultSetToArrayListFiltered(ResultSet resultSet)
			throws SQLException, DAOException {

		while (resultSet.next()) {
			UtilisateurDAO unUtilisateurDAO = daoFactory.getUtilisateurDAO();
			MedecinDAO unMedecinDAO = daoFactory.getMedecinDAO();
			Rdv a = new Rdv();
			a.setId(resultSet.getInt("id"));
			a.setUtilisateur(unUtilisateurDAO.findByRef(resultSet.getInt("id_utilisateur")));
			a.setMedecin(unMedecinDAO.findByRef(resultSet.getInt("id_medecin")));
			a.setDate(resultSet.getString("date"));
			a.setHeure(resultSet.getString("heure"));
			a.setCommentaire(resultSet.getString("commentaire"));	
			getListeRdvsTries().add(a);
		}
	}

	
}
