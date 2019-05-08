package fr.medoc.entities;

public class Utilisateur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String login;

	public Utilisateur() {

		this.login="";
	}
	
	public Utilisateur(String nom){
		
		this.login= nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "Utilisateur [login=" + login + "]";
	}

	

	

}
