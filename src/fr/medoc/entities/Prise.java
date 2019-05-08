package fr.medoc.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Prise {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private Utilisateur utilisateur;
	private Medicament medicament;
	private String datePrise;
	
	public Prise(Utilisateur unUtilisateur, Medicament unMedicament, String datePrise) {
	
		this.utilisateur = unUtilisateur;
		this.medicament = unMedicament;
		this.datePrise = datePrise;
	}

	public Prise() {
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("yyyy-MM-dd");
		Date aujourdhui = new Date();
		
		this.utilisateur = null;
		this.medicament = null;
		this.datePrise = formater.format(aujourdhui);
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

	public void setUtilisateur(Utilisateur unUtilisateur) {
		this.utilisateur = unUtilisateur;
	}

	public Medicament getMedicament() {
		return medicament;
	}

	public void setMedicament(Medicament unMedicament) {
		this.medicament = unMedicament;
	}

	public String getDatePrise() {
		return datePrise;
	}

	public void setDatePrise(String datePrise) {
		this.datePrise = datePrise;
	}

	@Override
	public String toString() {
		return "Prise [id=" + id + ", utilisateur=" + utilisateur + ", medicament=" + medicament
				+ ", datePrise=" + datePrise + "]";
	}

	
	
	

	

	

	

	
	
}
