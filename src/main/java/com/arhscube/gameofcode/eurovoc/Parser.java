package com.arhscube.gameofcode.eurovoc;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.arhscube.gameofcode.TestEurovocIndexing;

public class Parser {

	private static SAXReader reader = new SAXReader();
	private static final String TOKENIZER = "[ '/.,()]";
	private static Logger log = LoggerFactory.getLogger(TestEurovocIndexing.class);;

	public static HashMap<String, List<Term>> loadThesaurus(String resource) {
		Document doc;

		try {
			reader.setEntityResolver(new EntityResolver() {

				@Override
				public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
					log.info("Resolve public={} system={} ", publicId, systemId);
					String resource = systemId.substring(systemId.lastIndexOf("/"));
					log.info("get resource {} ", resource);
					InputStream is = TestEurovocIndexing.class.getResourceAsStream(resource);
					log.info("resolved {} ", is);
					return new InputSource(is);
				}
			});
			doc = reader.read(Parser.class.getResourceAsStream(resource));
		} catch (DocumentException e) {
			log.error("Can't load resource '{}',resource,e");
			return null;
		}
		@SuppressWarnings("unchecked")
		List<Element> records = DocumentHelper.createXPath("/DESCRIPTEUR/RECORD").selectNodes(doc);
		log.info(" size = {}", records.size());
		HashMap<String, List<Term>> descriptors = new HashMap<>();
		for (Element record : records) {
			Term term = new Term();
			term.id = record.element("DESCRIPTEUR_ID").getText();
			term.libelle = record.element("LIBELLE").getText().toLowerCase();
			String firstWord = tokenize(term.libelle)[0];
			if (!descriptors.containsKey(firstWord)) {
				descriptors.put(firstWord, new ArrayList<>());
			}
			descriptors.get(firstWord).add(term);
		}
		return descriptors;
	}

	private static String[] tokenize(String string) {
		// TODO Auto-generated method stub
		return string.toLowerCase().split(TOKENIZER);
	}

	public static List<Term> findDescriptors(String string, HashMap<String, List<Term>> descriptors) {
		String tokens[] = tokenize(string);
		List<Term> ret = new ArrayList<>();
		for (String token : tokens) {
			if (descriptors.containsKey(token)) {
				for (Term t : descriptors.get(token)) {
					if (string.indexOf(t.getLibelle()) > -1) {
						if (!ret.contains(t)) {
							log.debug("found {} in thesaurus", t);
							ret.add(t);
						}
					}
				}
			}
		}
		return ret;
	}

}
