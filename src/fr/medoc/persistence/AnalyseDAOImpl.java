package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import fr.medoc.dao.AnalyseDAO;
import fr.medoc.dao.CabinetDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.OrdonnanceDAO;
import fr.medoc.entities.Analyse;
import fr.medoc.enumeration.EnumAnalyse;
import fr.medoc.exception.DAOException;

public class AnalyseDAOImpl implements AnalyseDAO {

	private ArrayList<Analyse> listeAnalyses;
	private final String ORDRE_INSERT = "insert into analyse(id_ordonnance, nom, id_cabinet, commentaire, date) values ";
	private final String VALUES_INSERT = "(?,?,?,?,?)";
	private final String ORDRE_DELETE = "delete from analyse where Id = ";
	private final String ORDRE_FINDALL = "select * from analyse";
	private final String ORDRE_FINDBYREF = "select * from analyse where Id = ?";
	private final String ORDRE_UPDATE = "update analyse set id_ordonnance=?, set nom=?,set id_cabinet=?,set commentaire=?, set date=? where id = ?";
	//a faire
	private final String ORDRE_FINDALLBYUSER = "select a.id,a.id_ordonnance,a.nom,a.id_cabinet,a.commentaire,a.date from analyse AS a, ordonnance as o where a.id_ordonnance=o.id and o.id_utilisateur=?";
	
	private DAOFactory daoFactory;

	public AnalyseDAOImpl(DAOFactory daoFactory) {
		listeAnalyses = new ArrayList<Analyse>();
		this.daoFactory = daoFactory;
	}

	@Override
	public void modifierAnalyse(Analyse uneAnalyse, int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeAnalyses().add(uneAnalyse);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_UPDATE);
			pst.setInt(1, uneAnalyse.getOrdonnance().getId());
			pst.setString(2, uneAnalyse.getNom().name());
			pst.setInt(3, uneAnalyse.getCabinet().getId());
			pst.setString(4, uneAnalyse.getCommentaire());
			pst.setString(5, uneAnalyse.getDate());
			pst.setInt(6, uneAnalyse.getId());
			pst.executeUpdate();
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void ajouterAnalyse(Analyse uneAnalyse) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListeAnalyses().add(uneAnalyse);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, uneAnalyse.getOrdonnance().getId());
			pst.setString(2, uneAnalyse.getNom().name());
			pst.setInt(3, uneAnalyse.getCabinet().getId());
			pst.setString(4, uneAnalyse.getCommentaire());
			pst.setString(5, uneAnalyse.getDate());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				uneAnalyse.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'une analyse. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void supprimerAnalyse(int idAnalyse) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'" + idAnalyse + "'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Analyse findByRef(int id) throws DAOException {
		Analyse uneAnalyse = null;
		Connection connexion = null;
		OrdonnanceDAO uneOrdonnanceDAO = daoFactory.getOrdonnanceDAO();
		CabinetDAO unCabinetDAO = daoFactory.getCabinetDAO();
		
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				uneAnalyse = new Analyse();
				uneAnalyse.setId(id);
				uneAnalyse.setOrdonnance(uneOrdonnanceDAO.findByRef(rs.getInt("id_ordonnance")));
				uneAnalyse.setNom(EnumAnalyse.valueOf(rs.getString("nom")));
				uneAnalyse.setCabinet(unCabinetDAO.findByRef(rs.getInt("id_cabinet")));
				uneAnalyse.setCommentaire(rs.getString("commentaire"));
				uneAnalyse.setDate(rs.getString("date"));
			} else {
				throw new DAOException("Erreur recherche d'une analyse. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return uneAnalyse;
	}

	@Override
	public Collection<Analyse> findAll() throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALL);
			ResultSet resultSet = pst.executeQuery();
			listeAnalyses.removeAll(listeAnalyses);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeAnalyses;
	}

	public void setListeAnalyses(ArrayList<Analyse> listeAnalyses) {
		this.listeAnalyses = listeAnalyses;
	}

	public Collection<Analyse> getListeAnalyses() {
		return listeAnalyses;
	}

	private void resultSetToArrayList(ResultSet resultSet) throws SQLException, DAOException {
		while (resultSet.next()) {
			Analyse a = new Analyse();
			OrdonnanceDAO uneOrdonnanceDAO = daoFactory.getOrdonnanceDAO();
			CabinetDAO unCabinetDAO = daoFactory.getCabinetDAO();
			
			a.setId(resultSet.getInt("id"));
			a.setOrdonnance(uneOrdonnanceDAO.findByRef(resultSet.getInt("id_ordonnance")));
			a.setNom(EnumAnalyse.valueOf(resultSet.getString("nom")));
			a.setCabinet(unCabinetDAO.findByRef(resultSet.getInt("id_cabinet")));
			a.setCommentaire(resultSet.getString("commentaire"));
			a.setDate(resultSet.getString("date"));
			getListeAnalyses().add(a);
		}
	}

	@Override
	public Collection<Analyse> findAllByUser(int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLBYUSER);
			pst.setInt(1, id);
			ResultSet resultSet = pst.executeQuery();
			listeAnalyses.removeAll(listeAnalyses);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listeAnalyses;
		
	}
}
