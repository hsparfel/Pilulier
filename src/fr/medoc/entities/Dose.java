package fr.medoc.entities;

public class Dose {

	private int id;
	private String nom;

	public Dose() {
		this.nom="";
	}

	public Dose(String nom){
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
		return "Dose [id=" + id + ", nom=" + nom + "]";
	}
}
