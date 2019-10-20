package fr.medoc.enumeration;

public enum EnumExamen_old {

	//RAD("radiographie"), ECH("echographie"), PER("perfusion"), IRM("irm");
NON("a nettoyer");
	private String name = "";

	EnumExamen_old(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
