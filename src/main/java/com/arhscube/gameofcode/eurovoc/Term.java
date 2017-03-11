package com.arhscube.gameofcode.eurovoc;
public class Term {
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Term [id=" + id + ", libelle=" + libelle + "]";
	}

	String id,libelle;
}
