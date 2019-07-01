package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.OrdonnanceDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.MedecinDAO;
import fr.medoc.entities.Ordonnance;
import fr.medoc.exception.DAOException;

public class OrdonnanceDAOImpl implements OrdonnanceDAO {

	private ArrayList<Ordonnance> listeOrdonnances;
	
	private final String ORDRE_INSERT = "insert into ordonnance(id_utilisateur,id_medecin,date,commentaire) values ";
	private final String VALUES_INSERT = "(?,?,?,?)";
	private final String ORDRE_FINDBYREF = "select * from ordonnance where Id = ?";
	private final String ORDRE_FINDALLBYUSER = "select * from ordonnance AS o where o.id_utilisateur=?";
	private final String ORDRE_DELETE = "delete from ordonnance where Id = ";
	private final String ORDRE_UPDATE = "update ordonnance set id_utilisateur=?,id_medicament=?,date=?,commentaire=?  where id = ?";

	private DAOFactory daoFactory;

	public OrdonnanceDAOImpl(DAOFactory daoFactory) {
		listeOrdonnances = new ArrayList<Ordonnance>();
		this.daoFactory = daoFactory;
	}

	@Override
	public Ordonnance findByRef(int id) throws DAOException {
		Ordonnance uneOrdonnance = null;
		Connection connexion = null;
		UtilisateurDAO unUtilisateurDAO = daoFactory.getUtilisateurDAO();
		MedecinDAO unMedecinDAO = daoFactory.getMedecinDAO();
		
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneOrdonnance = new Ordonnance();
				uneOrdonnance.setId(rs.getInt("id"));
				uneOrdonnance.setUtilisateur(unUtilisateurDAO.findByRef(rs.getInt("id_utilisateur")));
				uneOrdonnance.setMedecin(unMedecinDAO.findByRef(rs.getInt("id_medecin")));
				uneOrdonnance.setDate(rs.getString("date"));
				uneOrdonnance.setCommentaire(rs.getString("commentaire"));
			} else {
				throw new DAOException("Erreur recherche d'une ordonnance");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneOrdonnance;
	}

	@Override
	public void modifierOrdonnance(Ordonnance uneOrdonnance, int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeOrdonnances().add(uneOrdonnance);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_UPDATE);
			pst.setInt(1, uneOrdonnance.getUtilisateur().getId());
			pst.setInt(2, uneOrdonnance.getMedecin().getId());
			pst.setString(3, uneOrdonnance.getDate());
			pst.setString(4, uneOrdonnance.getCommentaire());
			pst.executeUpdate();
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void supprimerOrdonnance(int idOrdonnance) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'" + idOrdonnance + "'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void ajouterOrdonnance(Ordonnance uneOrdonnance) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListeOrdonnances().add(uneOrdonnance);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, uneOrdonnance.getUtilisateur().getId());
			pst.setInt(2, uneOrdonnance.getMedecin().getId());
			pst.setString(3, uneOrdonnance.getDate());
			pst.setString(4, uneOrdonnance.getCommentaire());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				uneOrdonnance.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'une ordonnance. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Collection<Ordonnance> findAllByUser(int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLBYUSER);
			pst.setInt(1, id);
			ResultSet resultSet = pst.executeQuery();
			listeOrdonnances.removeAll(listeOrdonnances);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeOrdonnances;
	}

	public void setListeOrdonnances(ArrayList<Ordonnance> listeOrdonnances) {
		this.listeOrdonnances = listeOrdonnances;
	}

	public Collection<Ordonnance> getListeOrdonnances() {
		return listeOrdonnances;
	}

	private void resultSetToArrayList(ResultSet resultSet) throws SQLException, DAOException {

		while (resultSet.next()) {
			Ordonnance a = new Ordonnance();
			UtilisateurDAO unUtilisateurDAO = daoFactory.getUtilisateurDAO();
			MedecinDAO unMedecinDAO = daoFactory.getMedecinDAO();
			a.setId(resultSet.getInt("id"));
			a.setUtilisateur(unUtilisateurDAO.findByRef(resultSet.getInt("id_utilisateur")));
			a.setMedecin(unMedecinDAO.findByRef(resultSet.getInt("id_medecin")));
			a.setDate(resultSet.getString("date"));
			a.setCommentaire(resultSet.getString("commentaire"));
			getListeOrdonnances().add(a);
		}
	}
}
