package fr.medoc.entities;

public class Utilisateur {

	

	private int id;
	private String nom;

	public Utilisateur() {
		this.nom = "";
	}

	public Utilisateur(String nom) {
		this.nom = nom;
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
		return "Utilisateur [id=" + id + ", nom=" + nom + "]";
	}

}
