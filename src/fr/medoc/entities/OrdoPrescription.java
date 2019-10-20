package fr.medoc.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fr.medoc.enumeration.EnumDuree;

public class OrdoPrescription {

	private int id;
	private Ordonnance ordonnance;
	private Medicament medicament;
	private float nbDose;
	private Dose dose;
	private int nbFrequence;
	private EnumDuree frequence;
	private int nbDuree;
	private EnumDuree duree;
	private String dateDebut;
	private String dateFin;
	private int matin;
	private int midi;
	private int soir;
	private String commentaire;


	public OrdoPrescription() {
	}



	public int getMatin() {
		return matin;
	}



	public void setMatin(int matin) {
		this.matin = matin;
	}



	public int getMidi() {
		return midi;
	}



	public void setMidi(int midi) {
		this.midi = midi;
	}



	public int getSoir() {
		return soir;
	}



	public void setSoir(int soir) {
		this.soir = soir;
	}



	public Ordonnance getOrdonnance() {
		return ordonnance;
	}



	public void setOrdonnance(Ordonnance ordonnance) {
		this.ordonnance = ordonnance;
	}



	public String calculerDateFin(String dateDebut, int nbDuree, EnumDuree duree) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate dateDebutParsedDate = LocalDate.parse(dateDebut, formatter);
		LocalDate dateFinParsedDate = null;
		switch (duree.name()) {
		case "JOU":
			dateFinParsedDate = dateDebutParsedDate.plusDays(nbDuree);
			break;
		case "SEM":
			dateFinParsedDate = dateDebutParsedDate.plusWeeks(nbDuree);
			break;
		case "MOI":
			dateFinParsedDate = dateDebutParsedDate.plusMonths(nbDuree);
			break;
		default:
			dateFinParsedDate = dateDebutParsedDate;
		}
		String dateFin = dateFinParsedDate.format(formatter);
		return dateFin;
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



	public Medicament getMedicament() {
		return medicament;
	}



	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
	}



	public float getNbDose() {
		return nbDose;
	}



	public void setNbDose(float nbDose) {
		this.nbDose = nbDose;
	}



	public Dose getDose() {
		return dose;
	}



	public void setDose(Dose dose) {
		this.dose = dose;
	}



	public int getNbFrequence() {
		return nbFrequence;
	}



	public void setNbFrequence(int nbFrequence) {
		this.nbFrequence = nbFrequence;
	}



	public EnumDuree getFrequence() {
		return frequence;
	}



	public void setFrequence(EnumDuree frequence) {
		this.frequence = frequence;
	}



	public int getNbDuree() {
		return nbDuree;
	}



	public void setNbDuree(int nbDuree) {
		this.nbDuree = nbDuree;
	}



	public EnumDuree getDuree() {
		return duree;
	}



	public void setDuree(EnumDuree duree) {
		this.duree = duree;
	}



	public String getDateDebut() {
		return dateDebut;
	}



	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}



	public String getDateFin() {
		return dateFin;
	}



	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}






}
