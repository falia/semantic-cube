package com.arhscube.gameofcode.eurovoc;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class Parser {

	private static SAXReader reader = new SAXReader();
	private static final String TOKENIZER = "[ '/.,()]";
	private static Logger log = LoggerFactory.getLogger(Parser.class);


	public enum LANG {
		DE, EN, FR, PT
	};

	public static LANG getLangCode(String lang){
		switch (lang){
			case "fr": return Parser.LANG.FR;
			case "de": return Parser.LANG.DE;
			case "en": return Parser.LANG.EN;
			case "pt": return Parser.LANG.PT;
			default:return LANG.EN;
		}
	}

	private static final Map<LANG, HashMap<String, List<Term>>> cache = new HashMap<>();
	private static final Map<LANG, List<Term>> cache2 = new HashMap<>();

	public static List<Term> getAllTerms(LANG lang) {
		if (cache2.containsKey(lang)) {
			return cache2.get(lang);
		}
		List<Term> ret = new ArrayList<>();
		Document terms;
		Document useFor;
		try {
			reader.setEntityResolver(new EntityResolver() {

				@Override
				public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
					log.info("Resolve public={} system={} ", publicId, systemId);
					String resource = systemId.substring(systemId.lastIndexOf("/") + 1);
					log.info("get resource {} ", resource);
					InputStream is = Parser.class.getResourceAsStream(resource);
					log.info("resolved {} ", is);
					return new InputSource(is);
				}
			});
			terms = reader.read(Parser.class.getResourceAsStream("desc_" + lang.toString().toLowerCase() + ".xml"));
			useFor = reader.read(Parser.class.getResourceAsStream("uf_" + lang.toString().toLowerCase() + ".xml"));
		} catch (DocumentException e) {
			log.error("Can't load resource for '{}'", lang, e);
			return null;
		}
		@SuppressWarnings("unchecked")
		List<Element> records = DocumentHelper.createXPath("/DESCRIPTEUR/RECORD").selectNodes(terms);
		log.info(" size = {}", records.size());

		for (Element record : records) {
			Term term = new Term();
			term.id = record.element("DESCRIPTEUR_ID").getText();
			term.label = record.element("LIBELLE").getText().toLowerCase();
			ret.add(term);
		}
		records = DocumentHelper.createXPath("/USED_FOR/RECORD").selectNodes(useFor);
		log.info(" size = {}", records.size());
		for (Element record : records) {
			List<Element> ufEls = DocumentHelper.createXPath("UF/UF_EL").selectNodes(record);
			for (Element ufEl : ufEls) {
				Term term = new Term();
				term.id = record.element("DESCRIPTEUR_ID").getText();
				term.label = ufEl.getText().toLowerCase();
				String firstWord = tokenize(term.label)[0];
				ret.add(term);
			}
		}
		return ret;
	}

	public static HashMap<String, List<Term>> loadThesaurus(LANG lang) {
		if (cache.containsKey(lang)) {
			return cache.get(lang);
		}
		HashMap<String, List<Term>> descriptors = new HashMap<>();
		for (Term term : getAllTerms(lang)) {
			String firstWord = tokenize(term.label)[0];
			if (!descriptors.containsKey(firstWord)) {
				descriptors.put(firstWord, new ArrayList<>());
			}
			descriptors.get(firstWord).add(term);
		}
		cache.put(lang, descriptors);
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
					if (string.indexOf(t.getLabel()) > -1) {
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
