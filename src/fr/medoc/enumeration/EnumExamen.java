package fr.medoc.enumeration;

public enum EnumExamen {

	RAD("radiographie"), ECH("echographie"), PER("perfusion"), IRM("irm");

	private String name = "";

	EnumExamen(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
