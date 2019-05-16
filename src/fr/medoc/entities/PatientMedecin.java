package fr.medoc.entities;

public class PatientMedecin {

	

	private Utilisateur patient;
	private Medecin medecin;
		
	public PatientMedecin(Utilisateur utilisateur, Medecin medecin ) {

		this.patient = utilisateur;
		this.medecin = medecin;
	}

	public PatientMedecin() {

		this.patient = null;
		this.medecin = null;
	}

	public Utilisateur getPatient() {
		return patient;
	}

	public void setPatient(Utilisateur patient) {
		this.patient = patient;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	@Override
	public String toString() {
		return "PatientMedecin [patient=" + patient + ", medecin=" + medecin + "]";
	}

	




	

	
}
