package com.arhscube.gameofcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TestEurovocIndexing {
	static Logger log ;
	Document fr, en, de;

	private static SAXReader reader = new SAXReader();

	@BeforeClass
	public static void initReader() {
		log= LoggerFactory.getLogger(TestEurovocIndexing.class);
		reader.setEntityResolver(new EntityResolver() {

			@Override
			public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
				log.info("Resolve public={} system={} ",publicId, systemId);
				String resource = systemId.substring(systemId.lastIndexOf("/"));
				log.info("get resource {} ",resource);
				InputStream is = TestEurovocIndexing.class.getResourceAsStream(resource);
				log.info("resolved {} ",is);
				return new InputSource(is);
			}
		});
	}

	@Before
	public void initDataFr() throws Exception {
		log.info("Reading fr");
		fr = reader.read(TestEurovocIndexing.class.getResourceAsStream("/desc_fr.xml"));
	}

	@Before
	public void initDataDe() throws Exception {
		log.info("Reading de");
		de = reader.read(TestEurovocIndexing.class.getResourceAsStream("/desc_de.xml"));
	}

	@Before
	public void initDataEn() throws Exception {
		log.info("Reading en");
		en = reader.read(TestEurovocIndexing.class.getResourceAsStream("/desc_en.xml"));
	}
	HashMap<String, String> fr_descriptors = new HashMap<>();
	@Test
	public void testFr() {
		assertNotNull(fr);
		List<Element> records = DocumentHelper.createXPath("/DESCRIPTEUR/RECORD").selectNodes(fr);
		log.info(" size = {}",records.size());
		for(Element record : records){
			String id= record.element("DESCRIPTEUR_ID").getText();
			String label=record.element("LIBELLE").getText(); 
			log.debug("{} = {}",id,label);
			fr_descriptors.put(label.toLowerCase(), id);
		}
		
		List<String> found = findDescriptors("Le fil de l'eau",fr_descriptors);
		assertEquals("found 2 descriptors", 2,found.size());
		log.info("Found '{}' descriptors in text",found);
	}

	private List<String> findDescriptors(String string,HashMap<String, String> descriptors) {
		String tokens[] = string.toLowerCase().split(" |'");
		List<String> ret = new ArrayList<>();
		for(String token:tokens){
			if(descriptors.containsKey(token)){
				log.debug("found {} in thesaurus",token);
				ret.add(descriptors.get(token));
			}
		}
		return ret;
	}

}
