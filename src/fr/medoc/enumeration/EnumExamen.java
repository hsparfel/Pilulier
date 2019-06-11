package fr.medoc.enumeration;

public enum EnumExamen {

	RAD("radio"), ECH("echographie"), PER("perfusion");

	private String name = "";

	EnumExamen(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
