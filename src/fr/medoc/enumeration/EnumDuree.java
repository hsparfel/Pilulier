package fr.medoc.enumeration;

public enum EnumDuree {

	JOU("jour"), SEM("semaine"), MOI("mois");

	private String name = "";

	EnumDuree(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
