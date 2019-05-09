package fr.medoc.entities;

public class Specialite {

	private static final long serialVersionUID = 1L;

	private int id;
	private String nom;
	

	public Specialite(String nom) {
		this.nom = nom;
			}

	public Specialite() {
		this.nom = "";
		
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
		return "Specialite [id=" + id + ", nom=" + nom + "]";
	}

	

}
