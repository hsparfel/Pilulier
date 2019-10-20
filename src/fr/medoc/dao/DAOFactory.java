package fr.medoc.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import fr.medoc.exception.DAOConfigurationException;
import fr.medoc.exception.DAOException;
import fr.medoc.persistence.*;

public class DAOFactory {

    private static final String FICHIER_PROPERTIES       = "fr/medoc/dao/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";

    private String              url;
    private String              username;
    private String              password;

    /* package */DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */
    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
        } catch ( FileNotFoundException e ) {
            throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.", e );
        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
        }

        DAOFactory instance = new DAOFactory( url, nomUtilisateur, motDePasse );
        return instance;
    }

    /* Méthode chargée de fournir une connexion à la base de données */
    public Connection getConnection() throws DAOException {
    	Connection connexion = null;
    	try {
	       	connexion = DriverManager.getConnection( url, username, password );
	        connexion.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DAOException("Connexion impossible.", e);
		}
	   	return connexion;
    }
	public void closeConnexion(Connection connexion) throws DAOException {
		try {
			connexion.close();
		} catch (SQLException e) {
			throw new DAOException("Fermeture connexion incorrecte.", e);
		}
	}


    /*
     * Méthodes de récupération de l'implémentation des différents DAO
     */
    public UtilisateurDAO getUtilisateurDAO() {
        return new UtilisateurDAOImpl(this);
    }

    public MedicamentDAO getMedicamentDAO() {
        return new MedicamentDAOImpl(this);
    }

        
    public OrdoPrescriptionDAO getOrdoPrescriptionDAO() {
        return new OrdoPrescriptionDAOImpl(this);
    }
    
    public OrdonnanceDAO getOrdonnanceDAO() {
        return new OrdonnanceDAOImpl(this);
    }
    
    public DoseDAO getDoseDAO() {
        return new DoseDAOImpl(this);
    }
    
    
    public PriseDAO getPriseDAO() {
        return new PriseDAOImpl(this);
    }
    
    public MedecinDAO getMedecinDAO() {
        return new MedecinDAOImpl(this);
    }
    
    public SpecialiteDAO getSpecialiteDAO() {
        return new SpecialiteDAOImpl(this);
    }
    
    public CabinetDAO getCabinetDAO() {
        return new CabinetDAOImpl(this);
    }
    
    public RdvDAO getRdvDAO() {
        return new RdvDAOImpl(this);
    }
    
    public ExamenDAO getExamenDAO() {
        return new ExamenDAOImpl(this);
    }
    
    public AnalyseDAO getAnalyseDAO() {
        return new AnalyseDAOImpl(this);
    }
    
    public OrdoExamenDAO getOrdoExamenDAO() {
        return new OrdoExamenDAOImpl(this);
    }
    
    public OrdoAnalyseDAO getOrdoAnalyseDAO() {
        return new OrdoAnalyseDAOImpl(this);
    }
    
    public PatientMedecinDAO getPatientMedecinDAO() {
        return new PatientMedecinDAOImpl(this);
    }
    
    public ProfilDAO getProfilDAO() {
        return new ProfilDAOImpl(this);
    }
}
