package fr.medoc.entities;

public class Frequence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nom;

	public Frequence() {

		this.nom="";
	}
	
	public Frequence(String nom){
		
		this.nom= nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Frequence [nom=" + nom + "]";
	}

	
	

	

	

}
