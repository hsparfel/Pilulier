package fr.medoc.entities;

public class Analyse {

	private int id;
	private String nom;

	public Analyse() {
		this.nom="";
	}

	public Analyse(String nom){
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
		return "Analyse [id=" + id + ", nom=" + nom + "]";
	}
}
