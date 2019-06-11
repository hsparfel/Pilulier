package fr.medoc.entities;

import fr.medoc.enumeration.EnumExamen;

public class Examen {

	private int id;
	private EnumExamen nom;
	private String commentaire;
	private String date;
	
	public Examen() {
		this.nom = null;
		this.commentaire = null;
		this.date = null;
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
