package fr.medoc.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Prise {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int idUtilisateur;
	private int idMedicament;
	private String datePrise;
	
	public Prise(int idUtilisateur, int idMedicament, String datePrise) {
	
		this.idUtilisateur = idUtilisateur;
		this.idMedicament = idMedicament;
		this.datePrise = datePrise;
	}

	public Prise() {
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("yyyy-MM-dd");
		Date aujourdhui = new Date();
		
		this.idUtilisateur = 0;
		this.idMedicament = 0;
		this.datePrise = formater.format(aujourdhui);
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDatePrise() {
		return datePrise;
	}

	public void setDatePrise(String datePrise) {
		this.datePrise = datePrise;
	}

	@Override
	public String toString() {
		return "Prise [id=" + id + ", idUtilisateur=" + idUtilisateur + ", idMedicament=" + idMedicament
				+ ", datePrise=" + datePrise + "]";
	}

	

	
	
}
