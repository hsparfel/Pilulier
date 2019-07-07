package fr.medoc.enumeration;

public enum EnumPoids {

	SOU("sous-poids"), NOR("poids normal"), SUR("surpoids");

	private String name = "";

	EnumPoids(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
