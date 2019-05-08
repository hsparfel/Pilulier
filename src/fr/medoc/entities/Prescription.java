package fr.medoc.entities;

public class Prescription {

	private static final long serialVersionUID = 1L;

	private Utilisateur utilisateur;
	private Medicament medicament;
	private int nbDose;
	private Dose dose;
	private int nbFrequence;
	private Frequence frequence;
	
	public Prescription(Utilisateur utilisateur, Medicament medicament, int nbDose, Dose dose, int nbFrequence, Frequence frequence) {

		this.utilisateur = utilisateur;
		this.medicament = medicament;
		this.nbDose = nbDose;
		this.dose = dose;
		this.nbFrequence = nbFrequence;
		this.frequence = frequence;
	}

	public Prescription() {

		this.utilisateur = null;
		this.medicament = null;
		this.nbDose = 0;
		this.dose = null;
		this.nbFrequence = 0;
		this.frequence = null;
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

	public int getNbDose() {
		return nbDose;
	}

	public void setNbDose(int nbDose) {
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

	public Frequence getFrequence() {
		return frequence;
	}

	public void setFrequence(Frequence frequence) {
		this.frequence = frequence;
	}

	@Override
	public String toString() {
		return "Prescription [utilisateur=" + utilisateur + ", medicament=" + medicament + ", nbDose=" + nbDose
				+ ", dose=" + dose + ", nbFrequence=" + nbFrequence + ", frequence=" + frequence + "]";
	}

	

	
}
