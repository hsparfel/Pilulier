package fr.medoc.enumeration;

public enum EnumSexe {

	MAL("Homme"), FEM("Femme");

	private String name = "";

	EnumSexe(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
