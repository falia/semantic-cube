package com.arhscube.gameofcode;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arhscube.gameofcode.eurovoc.Parser;
import com.arhscube.gameofcode.eurovoc.Term;

public abstract class AbstractEurovocBasedTest {
	static Logger log=LoggerFactory.getLogger(AbstractEurovocBasedTest.class);
	HashMap<String, List<Term>> fr_descriptors;
	@Before
	public void initDataFr() throws Exception {
		log.info("Reading fr");
		fr_descriptors = Parser.loadThesaurus("/desc_fr.xml");
	}
	HashMap<String, List<Term>> de_descriptors;
	@Before
	public void initDataDe() throws Exception {
		log.info("Reading de");
		de_descriptors = Parser.loadThesaurus("/desc_de.xml");
	}

	HashMap<String, List<Term>> en_descriptors;
	@Before
	public void initDataEn() throws Exception {
		log.info("Reading en");
		en_descriptors = Parser.loadThesaurus("/desc_en.xml");
	}

}
