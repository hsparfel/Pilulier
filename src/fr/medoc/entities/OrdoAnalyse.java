package fr.medoc.entities;

import fr.medoc.enumeration.EnumAnalyse_old;

public class OrdoAnalyse {

	private int id;
	private Ordonnance ordonnance;
	private Cabinet cabinet;
	private EnumAnalyse_old nom;
	private String commentaire;
	private String date;

	public OrdoAnalyse() {
	}





	public Ordonnance getOrdonnance() {
		return ordonnance;
	}





	public void setOrdonnance(Ordonnance ordonnance) {
		this.ordonnance = ordonnance;
	}





	public Cabinet getCabinet() {
		return cabinet;
	}





	public void setCabinet(Cabinet cabinet) {
		this.cabinet = cabinet;
	}





	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EnumAnalyse_old getNom() {
		return nom;
	}

	public void setNom(EnumAnalyse_old nom) {
		this.nom = nom;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
