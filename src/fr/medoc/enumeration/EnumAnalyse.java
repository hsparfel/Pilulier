package fr.medoc.enumeration;

public enum EnumAnalyse {

	SAN("sanguine"), SEL("selle"), URI("urine");

	private String name = "";

	EnumAnalyse(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
