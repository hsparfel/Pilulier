package fr.medoc.entities;

import fr.medoc.enumeration.EnumAnalyse;

public class Analyse {

	private int id;
	private EnumAnalyse nom;
	private String commentaire;
	private String date;
	
	public Analyse() {
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

	public EnumAnalyse getNom() {
		return nom;
	}

	public void setNom(EnumAnalyse nom) {
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
