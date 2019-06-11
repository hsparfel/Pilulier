package fr.medoc.entities;

import java.util.List;

public class Ordonnance {

	private int id;
	private Utilisateur utilisateur;
	private Medecin medecin;
	private List<Prescription2> listPrescriptions;
	private float nbPrescription;
	private List<Analyse> listAnalyses;
	private float nbAnalyse;
	private List<Examen> listExamens;
	private float nbExamen;
	private String date;
	private String commentaire;
	
	public Ordonnance() {
		this.utilisateur = null;
		this.medecin = null;		
		this.listPrescriptions = null;
		this.nbPrescription = 0;
		this.listAnalyses = null;
		this.nbAnalyse = 0;
		this.listExamens = null;
		this.nbExamen = 0;
		this.date = null;
		this.commentaire = null;
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

	public List<Prescription2> getListPrescriptions() {
		return listPrescriptions;
	}

	public void setListPrescriptions(List<Prescription2> listPrescriptions) {
		this.listPrescriptions = listPrescriptions;
	}

	public float getNbPrescription() {
		return nbPrescription;
	}

	public void setNbPrescription(float nbPrescription) {
		this.nbPrescription = nbPrescription;
	}

	public List<Analyse> getListAnalyses() {
		return listAnalyses;
	}

	public void setListAnalyses(List<Analyse> listAnalyses) {
		this.listAnalyses = listAnalyses;
	}

	public float getNbAnalyse() {
		return nbAnalyse;
	}

	public void setNbAnalyse(float nbAnalyse) {
		this.nbAnalyse = nbAnalyse;
	}

	public List<Examen> getListExamens() {
		return listExamens;
	}

	public void setListExamens(List<Examen> listExamens) {
		this.listExamens = listExamens;
	}

	public float getNbExamen() {
		return nbExamen;
	}

	public void setNbExamen(float nbExamen) {
		this.nbExamen = nbExamen;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


}
