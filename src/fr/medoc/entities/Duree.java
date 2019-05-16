package fr.medoc.entities;

public class Duree {

	private int id;
	private String nom;

	public Duree() {

		this.nom="";
	}
	
	public Duree(String nom){
		
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
		return "Duree [nom=" + nom + "]";
	}

	
	

	

	

}
