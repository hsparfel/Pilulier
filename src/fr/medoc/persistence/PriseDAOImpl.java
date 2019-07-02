package fr.medoc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

import fr.medoc.dao.PriseDAO;
import fr.medoc.dao.DAOFactory;
import fr.medoc.dao.Prescription2DAO;
import fr.medoc.entities.Prise;
import fr.medoc.exception.DAOException;

public class PriseDAOImpl implements PriseDAO {

	private ArrayList<Prise> listePrises;
	

	private final String ORDRE_INSERT = "insert into prise(id_prescription, date, heure) values ";
	private final String VALUES_INSERT = "(?,?,?)";
	private final String ORDRE_DELETE = "delete from prise where Id = ";
	private final String ORDRE_FINDALL = "select * from prise";
	//private final String ORDRE_FINDALLBYUSER = "select m.nom, pr.nb_dose, d.nom, p.date, p.heure from prise AS p, prescription AS pr, ordonnance AS o, medicament as m, dose AS d where p.id_prescription = pr.id and pr.id_ordonnance=o.id and pr.id_medicament=m.id and pr.id_dose = d.id and o.id_utilisateur=";
	private final String ORDRE_FINDALLBYUSER = "select p.id, p.id_prescription, p.date, p.heure, p.effectue from prise AS p, prescription AS pr, ordonnance AS o, medicament as m, dose AS d where p.id_prescription = pr.id and pr.id_ordonnance=o.id and pr.id_medicament=m.id and pr.id_dose = d.id and o.id_utilisateur=? and p.date=? order by p.heure";
	
	private final String ORDRE_FINDBYREF = "select * from prise where Id = ?";
	private final String ORDRE_DELETEBYPRESCRIPTION = "delete from prise where id_prescription = ";
	private final String ORDRE_VALIDATE = "update prise set effectue=true where id = ";

	private DAOFactory daoFactory;

	public PriseDAOImpl(DAOFactory daoFactory) {
		listePrises = new ArrayList<Prise>();
		
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouterPrise(Prise unePrise) throws DAOException {
		ResultSet rs = null;
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			getListePrises().add(unePrise);
			PreparedStatement pst = connexion.prepareStatement(ORDRE_INSERT + VALUES_INSERT,
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, unePrise.getPrescription().getId());
			pst.setString(2, unePrise.getDate());
			pst.setString(3, unePrise.getHeure());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				unePrise.setId(rs.getInt(1));
			} else {
				throw new DAOException("Erreur création d'une prise. ");
			}
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void supprimerPrise(int idPrise) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETE + "'" + idPrise + "'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void validerPrise(int idPrise) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_VALIDATE + "'" + idPrise + "'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void supprimerPrisesByPrescription(int idPrescription) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			Statement requete = connexion.createStatement();
			requete.executeUpdate(ORDRE_DELETEBYPRESCRIPTION + "'" + idPrescription + "'");
			connexion.commit();
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Prise findByRef(int id) throws DAOException {
		Prise unePrise = null;
		Connection connexion = null;
		Prescription2DAO unPrescriptionDAO = daoFactory.getPrescription2DAO();

		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDBYREF);
			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				//a revoir
				unePrise = new Prise(unPrescriptionDAO.findByRef(id), rs.getString("date"),rs.getString("heure"));

			} else {
				throw new DAOException("Erreur recherche d'une prise. ");
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

	

	private void resultSetToArrayList(ResultSet resultSet) throws SQLException, DAOException {

		while (resultSet.next()) {
			Prise a = new Prise();

			Prescription2DAO unPrescriptionDAO = daoFactory.getPrescription2DAO();
			a.setId(resultSet.getInt("id"));
			a.setPrescription(unPrescriptionDAO.findByRef(resultSet.getInt("id_prescription")));
			a.setDate(resultSet.getString("date"));
			a.setHeure(resultSet.getString("heure"));
			a.setEffectue(resultSet.getBoolean("effectue")); 

			getListePrises().add(a);
		}
	}

	@Override
	public Collection<Prise> findAllByUser(int id) throws DAOException {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement pst = connexion.prepareStatement(ORDRE_FINDALLBYUSER);
			pst.setInt(1, id);
			
			//String dateDuJour = "";
			LocalDate dateNow = LocalDate.now();
			
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		    String dateDuJour = dateNow.format(myFormatObj); 
		    System.out.println("date du jour: "+dateDuJour);
		     
			
			pst.setString(2, dateDuJour);
			ResultSet resultSet = pst.executeQuery();
			listePrises.removeAll(listePrises);
			resultSetToArrayList(resultSet);
			daoFactory.closeConnexion(connexion);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		System.out.println(listePrises);
		return listePrises;
	}

	/*private void resultSetToArrayListTries(ResultSet resultSet) throws SQLException, DAOException {

		while (resultSet.next()) {
			Prise a = new Prise();

			Prescription2DAO unPrescriptionDAO = daoFactory.getPrescription2DAO();

			a.setPrescription(unPrescriptionDAO.findByRef(resultSet.getInt("id_prescription")));
					a.setDate(resultSet.getString("date"));
			a.setHeure(resultSet.getString("heure"));

			getListePrisesTries().add(a);
		}
	}*/



}
