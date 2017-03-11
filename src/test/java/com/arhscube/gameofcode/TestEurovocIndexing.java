package com.arhscube.gameofcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
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
	static Logger log;
	HashMap<String, List<Term>> fr_descriptors;
	HashMap<String, List<Term>> de_descriptors;
	HashMap<String, List<Term>> en_descriptors;

	private static SAXReader reader = new SAXReader();
	private static final String TOKENIZER = " |'|/";
	@BeforeClass
	public static void initReader() {
		log = LoggerFactory.getLogger(TestEurovocIndexing.class);
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
	}

	@Before
	public void initDataFr() throws Exception {
		log.info("Reading fr");
		fr_descriptors = loadThesaurus("/desc_fr.xml");
	}
	class Term {
		@Override
		public String toString() {
			return "Term [id=" + id + ", libelle=" + libelle + "]";
		}

		String id,libelle;
	}
	private HashMap<String, List<Term>> loadThesaurus(String resource) {
		Document doc;
		try {
			doc = reader.read(this.getClass().getResourceAsStream(resource));
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
			term.id=record.element("DESCRIPTEUR_ID").getText();
			term.libelle=record.element("LIBELLE").getText();
			String firstWord = term.libelle.split(TOKENIZER)[0];
			if(!descriptors.containsKey(firstWord)){
				descriptors.put(firstWord,new ArrayList<>());
			}
			descriptors.get(firstWord).add(term);
		}
		return descriptors;
	}

	@Before
	public void initDataDe() throws Exception {
		log.info("Reading de");
		de_descriptors = loadThesaurus("/desc_de.xml");
	}

	@Before
	public void initDataEn() throws Exception {
		log.info("Reading en");
		en_descriptors = loadThesaurus("/desc_en.xml");
	}


	@Test
	public void testFr() {
		assertNotNull(fr_descriptors);
		List<Term> found = findDescriptors("Le fil de l'eau", fr_descriptors);
		assertEquals("found 2 descriptors", 2, found.size());
		log.info("Found '{}' descriptors in text", found);
		found = findDescriptors("Environnement/Eau Ce jeu de données contient: Assainissement des eaux usées 1963 - 2015Stations d'épuration (en équivalents-habitants) 2000 - 2015État des masses d'eaux de surface (en %) 2009 et 2015Stations d'épuration par canton et commune 2002 - 2013--------------------------------------- Automatically synched … descriptors ",fr_descriptors);
		log.info("Found '{}' descriptors in text", found);
		found = findDescriptors("Bruit/Kaméidi - Lärmkartierungen Le bruit, ou pollution sonore, représente pour beaucoup de citoyens une nuisance quotidienne. Les impacts sur l'être-humain peuvent être de nature physique et/ou psychique (diminution de la capacité de concentration, insomnie, stress, perte auditive, maladies cardio-vasculaires).D’autant plus, le …",fr_descriptors);
		log.info("Found '{}' descriptors in text", found);
		found = findDescriptors("Environnement/Forêts Ce jeu de données contient: Etat phytosanitaire (en %) 1986 - 2015Superficie des forêts (en ha) par type de peuplement et par type de propriétaire 2000 - 2010Superficie des terres boisées (en ha) par région écologique 2000 - 2010Superficie forestière (en ha) 1948 - 2002Superficie forestière par …",fr_descriptors);
		log.info("Found '{}' descriptors in text", found);
		found = findDescriptors("Ce jeu de données contient:  Heures travaillées (non-employées) par branche (NaceR2) (en 1000 heures travaillées) 2000 - 2015 Rémunération des salariés (D1) par branche (NaceR2) (en millions EUR) 2000 - 2015 Valeur ajoutée brute (B1) aux prix de base par branche (NaceR2)(en volume aux prix de l'année précédente chaînés; année de référence = 2010) (en millions EUR) 2000 - 2015 Heures travaillées total par branche (NaceR2) (en 1000 heures travaillées) 2000 - 2015 Emploi salarié (EEM) par branche (NaceR2) (en 1 000 personnes) 2000 - 2015 Consommation intermédiaire (P2) par branche (NaceR2)(en volume aux prix de l'année précédente chaînés; année de référence = 2010) (en millions EUR) 2000 - 2015 Emploi total (ETO) par branche (NaceR2) (en 1 000 personnes) 2000 - 2015 Travailleurs indépendants (ESE) par branche (NaceR2) (en 1 000 personnes) 2000 - 2015 Salaires et traitements bruts (D11) par branche (NaceR2) (en millions EUR) 2000 - 2015 Consommation intermédiaire (P2) par branche (NaceR2)(à prix courants) (en millions EUR) 2000 - 2015 Production (P1) par branche (NaceR2)(à prix courants) (en millions EUR) 2000 - 2015 Valeur ajoutée brute (B1) aux prix de base par branche (NaceR2)(à prix courants) (en millions EUR) 2000 - 2015 Production (P1) par branche (NaceR2)(en volume aux prix de l'année précédente chaînés; année de référence = 2010) (en millions EUR) 2000 - 2015 Heures travaillées (employées) par branche (NaceR2) (en 1000 heures travaillées) 2000 - 2015 Consommation de capital fixe (K1) par branche (NaceR2) (en millions EUR) 2000 - 2015 Structure de la somme des valeurs ajoutées brutes aux prix de base (NaceR2) (en % du total) 2000 - 2015 ---------------------------------------  Automatically synched from portail statistique (category Comptes nationaux/Comptes annuels - Agrégats par branche)",fr_descriptors);
		log.info("Found '{}' descriptors in text", found);
	}

	private List<Term> findDescriptors(String string, HashMap<String, List<Term>> descriptors) {
		String tokens[] = string.toLowerCase().split(TOKENIZER);
		List<Term> ret = new ArrayList<>();
		for (String token : tokens) {
			if (descriptors.containsKey(token)) {
				for(Term t:descriptors.get(token)){
					if(string.indexOf(t.libelle)>-1){
						if(!ret.contains(t)){
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
