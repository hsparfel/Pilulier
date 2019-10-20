package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.DoseDAO;
import fr.medoc.dao.MedicamentDAO;
import fr.medoc.dao.OrdonnanceDAO;
import fr.medoc.dao.OrdoPrescriptionDAO;
import fr.medoc.entities.OrdoPrescription;
import fr.medoc.enumeration.EnumDuree;
import fr.medoc.exception.DAOException;

public class OrdoPrescriptionDAOImpl implements OrdoPrescriptionDAO {

	private ArrayList<OrdoPrescription> listePrescriptions;

	///a revoir entierement
	private final String ORDRE_INSERT = "insert into ordo_prescription(id_ordonnance,id_medicament,nb_dose,id_dose,nb_frequence,frequence,nb_duree,duree,date_debut,date_fin,matin,midi,soir,commentaire) values ";
	private final String VALUES_INSERT = "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String ORDRE_FINDBYREF = "select * from ordo_prescription where Id = ?";
	private final String ORDRE_FINDALLBYUSER = "select p.id,p.id_ordonnance,p.id_medicament,p.nb_dose,p.id_dose,p.nb_frequence,p.frequence,p.nb_duree,p.duree,p.date_debut,p.date_fin,p.matin,p.midi,p.soir,p.commentaire from ordo_prescription AS p, ordonnance as o where p.id_ordonnance=o.id and o.id_utilisateur=? order by (SUBSTRING(p.date_fin,7,4)), (SUBSTRING(p.date_fin,4,2)), (SUBSTRING(p.date_fin,1,2))";
	private final String ORDRE_FINDBYORDONNANCE = "select * from ordo_prescription where Id_ordonnance = ?";
	//private final String ORDRE_FINDBYREFS = "select * from ordo_prescription AS um where um.id_utilisateur=? AND um.id_medicament=?";
	private final String ORDRE_DELETE = "delete from ordo_prescription where Id = ";
	private final String ORDRE_UPDATE = "update ordo_prescription set id_ordonnance=?,id_medicament=?,nb_dose=?,id_dose=?,nb_frequence=?,frequence=?,nb_duree=?,duree=?,date_debut=?,date_fin=?,matin=?,midi=?,soir=?,commentaire=?  where id = ?";

	private DAOFactory daoFactory;

	public OrdoPrescriptionDAOImpl(DAOFactory daoFactory) {
		listePrescriptions = new ArrayList<OrdoPrescription>();

		this.daoFactory = daoFactory;
	}

	@Override
	public OrdoPrescription findByRef(int id) throws DAOException {
		OrdoPrescription unePrescription = null;
		Connection connexion = null;
		OrdonnanceDAO uneOrdonnanceDAO = daoFactory.getOrdonnanceDAO();
		MedicamentDAO unMedicamentDAO = daoFactory.getMedicamentDAO();
		DoseDAO unDoseDAO = daoFactory.getDoseDAO();

		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				unePrescription = new OrdoPrescription();

				unePrescription.setId(rs.getInt("id"));
				unePrescription.setOrdonnance(uneOrdonnanceDAO.findByRef(rs.getInt("id_ordonnance")));
				unePrescription.setMedicament(unMedicamentDAO.findByRef(rs.getInt("id_medicament")));
				unePrescription.setNbDose(rs.getFloat("nb_dose"));
				unePrescription.setDose(unDoseDAO.findByRef(rs.getInt("id_dose")));
				unePrescription.setNbFrequence(rs.getInt("nb_frequence"));
				unePrescription.setFrequence(EnumDuree.valueOf(rs.getString("frequence")));
				unePrescription.setNbDuree(rs.getInt("nb_duree"));
				unePrescription.setDuree(EnumDuree.valueOf(rs.getString("duree")));
				unePrescription.setDateDebut(rs.getString("date_debut"));
				unePrescription.setDateFin(rs.getString("date_fin"));

				unePrescription.setMatin(rs.getInt("matin"));
				unePrescription.setMidi(rs.getInt("midi"));
				unePrescription.setSoir(rs.getInt("soir"));
				unePrescription.setCommentaire(rs.getString("commentaire"));
			} else {
				throw new DAOException("Erreur recherche d'une prescription");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return unePrescription;
	}

	@Override
	public void modifierPrescription(OrdoPrescription unePrescription, int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			getListePrescriptions().add(unePrescription);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_UPDATE);

			pst.setInt(1, unePrescription.getOrdonnance().getId());
			pst.setInt(2, unePrescription.getMedicament().getId());
			pst.setFloat(3, unePrescription.getNbDose());
			pst.setInt(4, unePrescription.getDose().getId());
			pst.setInt(5, unePrescription.getNbFrequence());
			pst.setString(6, ""+unePrescription.getFrequence());
			pst.setInt(7, unePrescription.getNbDuree());
			pst.setString(8, ""+unePrescription.getDuree());
			pst.setString(9, unePrescription.getDateDebut());
			pst.setString(10, unePrescription.getDateFin());
			pst.setInt(11, unePrescription.getMatin());
			pst.setInt(12, unePrescription.getMidi());
			pst.setInt(13, unePrescription.getSoir());
			pst.setString(14, unePrescription.getCommentaire());
			pst.setInt(15, id);

			pst.executeUpdate();
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void supprimerPrescription(int idPrescription) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'" + idPrescription + "'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void ajouterPrescription(OrdoPrescription unePrescription) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListePrescriptions().add(unePrescription);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, unePrescription.getOrdonnance().getId());
			pst.setInt(2, unePrescription.getMedicament().getId());
			pst.setFloat(3, unePrescription.getNbDose());
			pst.setInt(4, unePrescription.getDose().getId());
			pst.setInt(5, unePrescription.getNbFrequence());
			pst.setString(6, unePrescription.getFrequence().name());
			pst.setInt(7, unePrescription.getNbDuree());
			pst.setString(8, unePrescription.getDuree().name());
			pst.setString(9, unePrescription.getDateDebut());
			pst.setString(10, unePrescription.getDateFin());
			pst.setInt(11, unePrescription.getMatin());
			pst.setInt(12, unePrescription.getMidi());
			pst.setInt(13, unePrescription.getSoir());
			pst.setString(14, unePrescription.getCommentaire());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				unePrescription.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'une prescription. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Collection<OrdoPrescription> findAllByUser(int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLBYUSER);
			pst.setInt(1, id);
			ResultSet resultSet = pst.executeQuery();
			listePrescriptions.removeAll(listePrescriptions);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listePrescriptions;
	}

	public void setListePrescriptions(ArrayList<OrdoPrescription> listePrescriptions) {
		this.listePrescriptions = listePrescriptions;
	}

	public Collection<OrdoPrescription> getListePrescriptions() {
		return listePrescriptions;
	}





	private void resultSetToArrayList(ResultSet resultSet) throws SQLException, DAOException {

		while (resultSet.next()) {
			OrdoPrescription a = new OrdoPrescription();
			OrdonnanceDAO uneOrdonnanceDAO = daoFactory.getOrdonnanceDAO();
			MedicamentDAO unMedicamentDAO = daoFactory.getMedicamentDAO();
			DoseDAO unDoseDAO = daoFactory.getDoseDAO();

			a.setId(resultSet.getInt("id"));
			a.setOrdonnance(uneOrdonnanceDAO.findByRef(resultSet.getInt("id_ordonnance")));
			a.setMedicament(unMedicamentDAO.findByRef(resultSet.getInt("id_medicament")));
			a.setNbDose(resultSet.getFloat("nb_dose"));
			a.setDose(unDoseDAO.findByRef(resultSet.getInt("id_dose")));
			a.setNbFrequence(resultSet.getInt("nb_frequence"));
			a.setFrequence(EnumDuree.valueOf(resultSet.getString("frequence")));
			a.setNbDuree(resultSet.getInt("nb_duree"));
			a.setDuree(EnumDuree.valueOf(resultSet.getString("duree")));
			a.setDateDebut(resultSet.getString("date_debut"));
				a.setDateFin(resultSet.getString("date_fin"));
			a.setMatin(resultSet.getInt("matin"));
			a.setMidi(resultSet.getInt("midi"));
			a.setSoir(resultSet.getInt("soir"));
			a.setCommentaire(resultSet.getString("commentaire"));
			//a.setDateFin(a.calculerDateFin(resultSet.getString("date_debut"), a.getNbDuree(), a.getDuree()));
			getListePrescriptions().add(a);
		}
	}

	@Override
	public Collection<OrdoPrescription> findByOrdonnance(int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYORDONNANCE);
			pst.setInt(1, id);
			ResultSet resultSet = pst.executeQuery();
			listePrescriptions.removeAll(listePrescriptions);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return listePrescriptions;
	}

}
