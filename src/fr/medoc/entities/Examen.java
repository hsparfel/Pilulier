package fr.medoc.entities;

import fr.medoc.enumeration.EnumExamen;

public class Examen {

	private int id;
	private Ordonnance ordonnance;
	private Cabinet cabinet;
	private EnumExamen nom;
	private String commentaire;
	private String date;
	
	public Examen() {
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

	public EnumExamen getNom() {
		return nom;
	}

	public void setNom(EnumExamen nom) {
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
