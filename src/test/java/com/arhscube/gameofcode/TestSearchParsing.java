package com.arhscube.gameofcode;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arhscube.gameofcode.eurovoc.Parser.LANG;
import com.arhscube.gameofcode.search.Parser;
import com.arhscube.gameofcode.search.SearchTree;

public class TestSearchParsing {
	static Logger log = LoggerFactory.getLogger(TestSearchParsing.class);

	@Test
	public void testEurovocFirst() {
		String search = "eau AND walferdange";
		SearchTree s = Parser.parse(search, LANG.FR);
		log.debug("search = {}", s);
	}

	@Test
	public void testEurovocSecond() {
		String search = "walferdange AND eau";
		SearchTree s = Parser.parse(search, LANG.FR);
		log.debug("search = {}", s);
	}

	@Test
	public void testNoOperands() {
		String search = "walferdange eau dans le jardin";
		SearchTree s = Parser.parse(search, LANG.FR);
		log.debug("search = {}", s);
	}

}
