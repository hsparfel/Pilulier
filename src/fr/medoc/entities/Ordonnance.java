package fr.medoc.entities;



import java.io.InputStream;

public class Ordonnance {

	private int id;
	private Utilisateur utilisateur;
	private Medecin medecin;
	private String date;
	private String commentaire;
	private InputStream fichier;

	public Ordonnance() {
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
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


	public void setFichier(InputStream inputStream) {
		this.fichier = inputStream;
	}

	public InputStream getFichier() {
		return fichier;
	}
}
