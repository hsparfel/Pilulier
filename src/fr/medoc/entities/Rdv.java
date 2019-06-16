package fr.medoc.entities;

public class Rdv {

	private int id;
	private Utilisateur utilisateur;
	private Medecin medecin;
	private String date;
	private String heure;
	private String commentaire;

	public Rdv() {
		this.utilisateur = null;
		this.medecin = null;
		this.date = "";
		this.heure = "";
		this.commentaire ="";
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

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	@Override
	public String toString() {
		return "Rdv [id=" + id + ", utilisateur=" + utilisateur + ", medecin=" + medecin + ", date=" + date + ", heure="
				+ heure + ", commentaire=" + commentaire + "]";
	}

	

	





}
