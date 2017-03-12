package com.arhscube.gameofcode.search;

import java.util.Collections;
import java.util.List;

import com.arhscube.gameofcode.EurovocAnalyser;
import com.arhscube.gameofcode.eurovoc.Parser.LANG;
import com.arhscube.gameofcode.eurovoc.Term;

public class Parser {
	public static SearchTree parse(String search, LANG lng) {
		search = search.trim();
		SearchTree ret = null;
		if (search.contains(" AND ")) {
			int ind = search.indexOf(" AND ");
			ret = new SearchOperator(parse(search.substring(0, ind), lng), OPERAND.AND,
					parse(search.substring(ind + 5), lng));
		} else if (search.contains(" OR ")) {
			int ind = search.indexOf(" OR ");
			ret = new SearchOperator(parse(search.substring(0, ind), lng), OPERAND.OR,
					parse(search.substring(ind + 4), lng));
		} else {
			List<Term> terms = EurovocAnalyser.analyse(search, lng);
			if (terms.size() == 0) {
				return new SearchLiteral(search.split(" "), LITERAL_TYPE.STRING);
			}
			Collections.sort(terms);
			for (Term t : terms) {
				int ind = search.indexOf(t.getLabel());
				if (ind > -1) {
					SearchLiteral l = new SearchLiteral(new String[] { t.getId() }, LITERAL_TYPE.EUROVOC);
					if (ind == 0 && search.length() == t.getLabel().length()) {
						return l;
					}
					if (ind == 0) {
						return new SearchOperator(l, OPERAND.AND,
								parse(search.substring(t.getLabel().length() + 1), lng));
					}
					if (ind + t.getLabel().length() == search.length()) {
						return new SearchOperator(parse(search.substring(0, ind), lng), OPERAND.AND, l);
					}
					return new SearchOperator(parse(search.substring(0, ind), lng), OPERAND.AND, new SearchOperator(l,
							OPERAND.AND, parse(search.substring(ind + t.getLabel().length() + 1), lng)));
				}

			}
		}
		return ret;
	}

}
