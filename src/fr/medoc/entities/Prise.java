package fr.medoc.entities;

public class Prise {
	
	private int id;
	private OrdoPrescription prescription;
	private String date;
	private String heure;
	private boolean effectue;
	
	public Prise(OrdoPrescription prescription, String date, String heure) {

		this.prescription = prescription;
		this.date = date;
		this.heure = heure;
	}

	public Prise() {
		}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrdoPrescription getPrescription() {
		return prescription;
	}

	public void setPrescription(OrdoPrescription prescription) {
		this.prescription = prescription;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public boolean isEffectue() {
		return effectue;
	}

	public void setEffectue(boolean effectue) {
		this.effectue = effectue;
	}

	@Override
	public String toString() {
		return "Prise [id=" + id + ", prescription=" + prescription + ", date=" + date + ", heure=" + heure
				+ ", effectue=" + effectue + "]";
	}

	
	
	
	
}
