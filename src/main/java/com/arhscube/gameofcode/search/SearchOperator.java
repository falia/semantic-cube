package com.arhscube.gameofcode.search;

public class SearchOperator implements SearchTree {
	SearchTree left, right;
	OPERAND operand;

	public SearchOperator(SearchTree left, OPERAND operand, SearchTree right) {
		super();
		this.left = left;
		this.right = right;
		this.operand = operand;
	}

	SearchTree getLeft() {
		return left;
	}

	SearchTree getRight() {
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
