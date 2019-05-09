package fr.medoc.entities;

public class Medecin {

	private static final long serialVersionUID = 1L;

	private int id;
	private String nom;
	private Specialite specialite;
	private Cabinet cabinet;
	private String telephone;
	private String email;

	public Medecin(String nom, Specialite specialite, Cabinet cabinet, String telephone, String email) {
		this.nom = nom;
		this.specialite = specialite;
		this.cabinet = cabinet;
		this.telephone = telephone;
		this.email = email;
	}

	public Medecin() {
		this.nom = "";
		this.specialite = null;
		this.cabinet = null;
		this.telephone = "";
		this.email = "";
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

	public Specialite getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}

	public Cabinet getCabinet() {
		return cabinet;
	}

	public void setCabinet(Cabinet cabinet) {
		this.cabinet = cabinet;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Medecin [id=" + id + ", nom=" + nom + ", specialite=" + specialite + ", cabinet=" + cabinet
				+ ", telephone=" + telephone + ", email=" + email + "]";
	}

}
