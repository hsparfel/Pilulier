package fr.medoc.entities;

public class Prise {
	
	private int id;
	private Utilisateur utilisateur;
	private Medicament medicament;
	private String datePrise;
	private String heurePrise;
	
	public Prise(Utilisateur utilisateur, Medicament medicament, String datePrise, String heurePrise) {

		this.utilisateur = utilisateur;
		this.medicament = medicament;
		this.datePrise = datePrise;
		this.heurePrise = heurePrise;
	}

	public Prise() {
		this.utilisateur = null;
		this.medicament = null;
		this.datePrise = "";
		this.heurePrise = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Medicament getMedicament() {
		return medicament;
	}

	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
	}

	public String getDatePrise() {
		return datePrise;
	}

	public void setDatePrise(String datePrise) {
		this.datePrise = datePrise;
	}

	public String getHeurePrise() {
		return heurePrise;
	}

	public void setHeurePrise(String heurePrise) {
		this.heurePrise = heurePrise;
	}

	@Override
	public String toString() {
		return "Prise [id=" + id + ", utilisateur=" + utilisateur + ", medicament=" + medicament + ", datePrise="
				+ datePrise + ", heurePrise=" + heurePrise + "]";
	}
	
	
	
}
