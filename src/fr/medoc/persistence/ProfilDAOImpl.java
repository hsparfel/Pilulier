package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import fr.medoc.dao.ProfilDAO;
import fr.medoc.dao.UtilisateurDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.entities.Profil;
import fr.medoc.exception.DAOException;

public class ProfilDAOImpl implements ProfilDAO {

	private ArrayList<Profil> listeProfils;
	private final String ORDRE_INSERT = "insert into profil(id_utilisateur, poids, taille, imc, commentaire, date) values ";
	private final String VALUES_INSERT = "(?,?,?,?,?,?)";
	private final String ORDRE_DELETE = "delete from profil where Id = ";
	private final String ORDRE_FINDALLBYUSER = "select id,id_utilisateur,poids,taille,imc,commentaire,date from profil where id_utilisateur=?";
	private final String ORDRE_FINDBYREF = "select id,id_utilisateur,poids,taille,imc,commentaire,date from profil where Id = ?";
	private final String ORDRE_UPDATE = "update profil set id_utilisateur=?,poids=?,taille=?,imc=?,commentaire=?,date=? where id = ?";
	private DAOFactory daoFactory;

	public ProfilDAOImpl(DAOFactory daoFactory) {
		listeProfils = new ArrayList<Profil>();
		this.daoFactory = daoFactory;
	}

	@Override
	public void modifierProfil(Profil unProfil, int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeProfils().add(unProfil);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_UPDATE);
			pst.setInt(1, unProfil.getUtilisateur().getId());
			pst.setFloat(2, unProfil.getPoids());
			pst.setInt(3, unProfil.getTaille());
			pst.setFloat(4, unProfil.getImc());
			pst.setString(5, unProfil.getCommentaire());
			pst.setString(6, unProfil.getDate());
			
			pst.executeUpdate();
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void ajouterProfil(Profil unProfil) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeProfils().add(unProfil);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setInt(1, unProfil.getUtilisateur().getId());
			pst.setFloat(2, unProfil.getPoids());
			pst.setInt(3, unProfil.getTaille());
			pst.setFloat(4, unProfil.getImc());
			pst.setString(5, unProfil.getCommentaire());
			pst.setString(6, unProfil.getDate());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				unProfil.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'un profil. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void supprimerProfil(int idProfil) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'" + idProfil + "'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Profil findByRef(int id) throws DAOException {
		Profil unProfil = null;
		Connection connexion = null;
		UtilisateurDAO unUtilisateurDAO = daoFactory.getUtilisateurDAO();
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unProfil = new Profil();
				unProfil.setId(id);
				unProfil.setUtilisateur(unUtilisateurDAO.findByRef(rs.getInt("id_utilisateur")));
				unProfil.setPoids(rs.getFloat("poids"));
				unProfil.setTaille(rs.getInt("taille"));
				unProfil.setImc(rs.getFloat("imc"));
				unProfil.setCommentaire(rs.getString("commentaire"));
				unProfil.setDate(rs.getString("date"));
								
			} else {
				throw new DAOException("Erreur recherche d'un profil. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unProfil;
	}

/*	@Override
	public Profil findByName(String nom) throws DAOException {
		Profil unProfil = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYNAME);
			pst.setString(1, nom);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unProfil = new Profil(rs.getString("nom"));
				unProfil.setId(rs.getInt("id"));
			} else {
				throw new DAOException("Erreur recherche d'un profil. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unProfil;
	}*/

	@Override
	public Collection<Profil> findAllByUser(int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLBYUSER);
			pst.setInt(1, id);
			ResultSet resultSet = pst.executeQuery();
			listeProfils.removeAll(listeProfils);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeProfils;
	}

	public void setListeProfils(ArrayList<Profil> listeProfils) {
		this.listeProfils = listeProfils;
	}

	public Collection<Profil> getListeProfils() {
		return listeProfils;
	}

	private void resultSetToArrayList(ResultSet resultSet) throws SQLException, DAOException {
		while (resultSet.next()) {
			UtilisateurDAO unUtilisateurDAO = daoFactory.getUtilisateurDAO();
			Profil a = new Profil();
					
			a.setId(resultSet.getInt("id"));
			a.setUtilisateur(unUtilisateurDAO.findByRef(resultSet.getInt("id_utilisateur")));
			a.setPoids(resultSet.getFloat("poids"));
			a.setTaille(resultSet.getInt("taille"));
			a.setImc(resultSet.getFloat("imc"));
			a.setCommentaire(resultSet.getString("commentaire"));
			a.setDate(resultSet.getString("date"));
			
			
			
			getListeProfils().add(a);
		}
	}
}
