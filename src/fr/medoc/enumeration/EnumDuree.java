package fr.medoc.enumeration;

public enum EnumDuree {

	JOU("jour"), MOI("mois"), SEM("semaine");

	private String name = "";

	EnumDuree(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
