package com.arhscube.gameofcode;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arhscube.gameofcode.eurovoc.Parser.LANG;
import com.arhscube.gameofcode.eurovoc.Term;

public class TestSearchParsing {
	static Logger log = LoggerFactory.getLogger(TestSearchParsing.class);

	enum OPERAND {
		AND, OR, EQUAL
	};

	enum LITERAL_TYPE {
		STRING, EUROVOC
	};

	enum TYPE {
		OPERATOR, LITERAL
	};

	interface Search {
		TYPE getType();
	};

	class SearchLiteral implements Search {
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

	class SearchOperator implements Search {
		Search left, right;
		OPERAND operand;

		public SearchOperator(Search left, OPERAND operand, Search right) {
			super();
			this.left = left;
			this.right = right;
			this.operand = operand;
		}

		Search getLeft() {
			return left;
		}

		Search getRight() {
			return right;
		}

		OPERAND getOperand() {
			return operand;
		}

		@Override
		public TYPE getType() {
			// TODO Auto-generated method stub
			return TYPE.OPERATOR;
		}
	}

	@Test
	public void test() {
		String search = "eau AND walferdange";
		Search s = parse(search, LANG.FR);
		log.debug("search = {}", s);
	}

	private Search parse(String search, LANG lng) {
		search = search.trim();
		Search ret = null;
		if (search.contains(" AND ")) {
			int ind = search.indexOf(" AND ");
			ret = new SearchOperator(parse(search.substring(0, ind), lng), OPERAND.AND,
					parse(search.substring(ind + 5), lng));
		} else if (search.contains(" OR ")) {
			int ind = search.indexOf(" OR ");
			ret = new SearchOperator(parse(search.substring(0, ind), lng), OPERAND.OR,
					parse(search.substring(ind + 5), lng));
		} else {
			List<Term> terms = EurovocAnalyser.analyse(search, lng);
			if (terms.size() == 0) {
				return new SearchLiteral(search.split(" "), LITERAL_TYPE.STRING);
			}
			for (Term t : terms) {
				int ind = search.indexOf(t.getLibelle());
				if (ind > -1) {
					SearchLiteral l = new SearchLiteral(new String[] { t.getId() }, LITERAL_TYPE.EUROVOC);
					if (ind == 0 && search.length() == t.getLibelle().length()) {
						return l;
					}
					if (ind == 0) {
						return new SearchOperator(l, OPERAND.AND,
								parse(search.substring(t.getLibelle().length() + 1), lng));
					}
					if (ind + t.getLibelle().length() == search.length()) {
						return new SearchOperator(parse(search.substring(0, ind), lng), OPERAND.AND, l);
					}
					return new SearchOperator(parse(search.substring(0, ind), lng), OPERAND.AND, new SearchOperator(l,
							OPERAND.AND, parse(search.substring(ind + t.getLibelle().length() + 1), lng)));
				}

			}
		}
		return ret;
	}
}
