package fr.medoc.entities;

public class Prescription {

	private static final long serialVersionUID = 1L;
	//a revoir
	private int idUtilisateur;
	private int idMedicament;
	private int nbDose;
	private int idDose;
	private int nbFrequence;
	private int idFrequence;
	
	public Prescription(int idUtilisateur, int idMedicament, int nbDose, int idDose, int nbFrequence, int idFrequence) {

		this.idUtilisateur = idUtilisateur;
		this.idMedicament = idMedicament;
		this.nbDose = nbDose;
		this.idDose = idDose;
		this.nbFrequence = nbFrequence;
		this.idFrequence = idFrequence;
	}

	public Prescription() {

		this.idUtilisateur = 0;
		this.idMedicament = 0;
		this.nbDose = 0;
		this.idDose = 0;
		this.nbFrequence = 0;
		this.idFrequence = 0;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public int getIdMedicament() {
		return idMedicament;
	}

	public void setIdMedicament(int idMedicament) {
		this.idMedicament = idMedicament;
	}

	public int getNbDose() {
		return nbDose;
	}

	public void setNbDose(int nbDose) {
		this.nbDose = nbDose;
	}

	public int getIdDose() {
		return idDose;
	}

	public void setIdDose(int idDose) {
		this.idDose = idDose;
	}

	public int getNbFrequence() {
		return nbFrequence;
	}

	public void setNbFrequence(int nbFrequence) {
		this.nbFrequence = nbFrequence;
	}

	public int getIdFrequence() {
		return idFrequence;
	}

	public void setIdFrequence(int idFrequence) {
		this.idFrequence = idFrequence;
	}

	@Override
	public String toString() {
		return "Prescription [idUtilisateur=" + idUtilisateur + ", idMedicament=" + idMedicament + ", nbDose=" + nbDose
				+ ", idDose=" + idDose + ", nbFrequence=" + nbFrequence + ", idFrequence=" + idFrequence + "]";
	}
	
}
