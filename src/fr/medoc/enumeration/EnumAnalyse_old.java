package fr.medoc.enumeration;

public enum EnumAnalyse_old {

	//SAN("sanguine"), SEL("selle"), URI("urine");
	NON("a nettoyer");
	private String name = "";

	EnumAnalyse_old(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
