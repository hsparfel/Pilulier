package fr.medoc.entities;

public class Cabinet {

	private static final long serialVersionUID = 1L;

	private int id;
	private String nom;
	private String adresse;
	private String cp;
	private String ville;
	
	public Cabinet(String nom, String adresse, String cp, String ville) {
		this.nom = nom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
	}

	public Cabinet() {
		this.nom = "";
		this.adresse = "";
		this.cp = "";
		this.ville = "";
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Cabinet [id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", cp=" + cp + ", ville=" + ville + "]";
	}

	

}
