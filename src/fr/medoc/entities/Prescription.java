package fr.medoc.entities;

public class Prescription {

	private int id;
	private Utilisateur utilisateur;
	private Medecin medecin;
	private Medicament medicament;
	private int nbDose;
	private Dose dose;
	private int nbFrequence;
	private Frequence frequence;
	private int matin;
	private int midi;
	private int soir;
	private int nbDuree;
	private Duree duree;
	private String date;
	
	public Prescription(Utilisateur utilisateur, Medecin medecin, Medicament medicament, int nbDose, Dose dose,
			int nbFrequence, Frequence frequence,  int nbDuree, Duree duree, String date) {
		this.utilisateur = utilisateur;
		this.medecin = medecin;
		this.medicament = medicament;
		this.nbDose = nbDose;
		this.dose = dose;
		this.nbFrequence = nbFrequence;
		this.frequence = frequence;
		
		this.nbDuree = nbDuree;
		this.duree = duree;
		this.date=date;
	}
	public Prescription() {
		this.utilisateur = null;
		this.medecin = null;
		this.medicament = null;
		this.nbDose = 0;
		this.dose = null;
		this.nbFrequence = 0;
		this.frequence = null;
		this.matin = 0;
		this.midi = 0;
		this.soir = 0;
		this.nbDuree = 0;
		this.duree = null;
		this.date="";
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
	public int getNbDuree() {
		return nbDuree;
	}
	public void setNbDuree(int nbDuree) {
		this.nbDuree = nbDuree;
	}
	public Duree getDuree() {
		return duree;
	}
	public void setDuree(Duree duree) {
		this.duree = duree;
	}
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Prescription [id=" + id + ", utilisateur=" + utilisateur + ", medecin=" + medecin + ", medicament="
				+ medicament + ", nbDose=" + nbDose + ", dose=" + dose + ", nbFrequence=" + nbFrequence + ", frequence="
				+ frequence + ", matin=" + matin + ", midi=" + midi + ", soir=" + soir + ", nbDuree=" + nbDuree
				+ ", duree=" + duree + ", date=" + date + "]";
	}
	
	
	
	



	

	
}
