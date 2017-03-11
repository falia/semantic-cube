package com.arhscube.gameofcode.search;

public class SearchLiteral implements SearchTree {
	String values[];
	LITERAL_TYPE type;

	public SearchLiteral(String[] values, LITERAL_TYPE type) {
		super();
		this.values = values;
		this.type = type;
	}

	String[] getValues() {
		return values;
	}

	LITERAL_TYPE getLiteralType() {
		return type;
	}

	@Override
	public TYPE getType() {
		// TODO Auto-generated method stub
		return TYPE.LITERAL;
	}
}
