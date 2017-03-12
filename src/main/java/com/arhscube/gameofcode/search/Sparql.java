package com.arhscube.gameofcode.search;

public class Sparql {
	public static String toSparql(SearchTree st) {
		boolean isEurovoc = containsEurovoc(st);
		boolean isTextual = containsText(st);
		StringBuilder sb = new StringBuilder();
		sb.append("PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n");
		sb.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n");
		sb.append("SELECT ?dataset ?description ?title\n");
		sb.append("{\n");
		sb.append("\t?dataset rdf:type <http://www.w3.org/ns/dcat#Dataset> .\n");
		sb.append("\t?dataset <http://purl.org/dc/terms/description> ?description .\n");
		sb.append("\t?dataset <http://purl.org/dc/terms/title> ?title .\n");
		if (isEurovoc) {
			sb.append("\t?dataset <http://purl.org/dc/terms/theme> ?eurovoc.\n");
		}
		sb.append("\t");
		sb.append(toFilterClause(st));
		sb.append("\n}");
		return sb.toString();
	}

	private static boolean containsEurovoc(SearchTree st) {
		switch (st.getType()) {
		case LITERAL:
			SearchLiteral sl = (SearchLiteral) st;
			switch (sl.getLiteralType()) {
			case EUROVOC:
				return true;

			}
			return false;
		case OPERATOR:
			SearchOperator so = (SearchOperator) st;
			return containsEurovoc(so.getLeft()) || containsEurovoc(so.right);
		}
		return false;
	}

	private static boolean containsText(SearchTree st) {
		switch (st.getType()) {
		case LITERAL:
			SearchLiteral sl = (SearchLiteral) st;
			switch (sl.getLiteralType()) {
			case STRING:
				return true;
			}
			return false;
		case OPERATOR:
			SearchOperator so = (SearchOperator) st;
			return containsEurovoc(so.getLeft()) || containsEurovoc(so.right);
		}
		return false;
	}

	private static String toFilterClause(SearchTree st) {
		return "filter(" + filter(st) + ")";
	}

	private static String filter(SearchTree st) {
		switch (st.getType()) {
		case LITERAL:
			return filterLiteral((SearchLiteral) st);
		case OPERATOR:
			return filterOperator((SearchOperator) st);
		}
		// not possible...
		return null;
	}

	private static String filterOperator(SearchOperator st) {
		switch (st.getOperand()) {
		case AND:
			return "(" + filter(st.getLeft()) + " && " + filter(st.getRight()) + ")";
		case OR:
			return "(" + filter(st.getLeft()) + " || " + filter(st.getRight()) + ")";
		}
		// Not possible
		return null;
	}

	private static String filterLiteral(SearchLiteral st) {
		switch (st.getLiteralType()) {
		case EUROVOC:
			return join(st.values, "str(?eurovoc) = \"http://eurovoc.europa.eu/$val\"", " && ");
		case STRING:
			return "(" + join(st.values, "contains(?descriptor, \"$val\")", " && ") + " || "
					+ join(st.values, "contains(?title, \"$val\")", " && ") + ")";
		}
		return null;
	}

	private static String join(String[] values, String template, String join) {
		StringBuilder sb = null;
		for (String val : values) {
			if (sb == null) {
				sb = new StringBuilder();
			} else {
				sb.append(join);
			}
			sb.append(template.replace("$val", val));
		}
		return sb.toString();
	}
}
