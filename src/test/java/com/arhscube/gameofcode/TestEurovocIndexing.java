package com.arhscube.gameofcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arhscube.gameofcode.eurovoc.Parser;
import com.arhscube.gameofcode.eurovoc.Parser.LANG;
import com.arhscube.gameofcode.eurovoc.Term;

public class TestEurovocIndexing {
	static Logger log = LoggerFactory.getLogger(TestEurovocIndexing.class);
	HashMap<String, List<Term>> fr_descriptors;

	@Before
	public void initDataFr() throws Exception {
		log.info("Reading fr");
		fr_descriptors = Parser.loadThesaurus(LANG.FR);
	}

	HashMap<String, List<Term>> de_descriptors;

	@Before
	public void initDataDe() throws Exception {
		log.info("Reading de");
		de_descriptors = Parser.loadThesaurus(LANG.DE);
	}

	HashMap<String, List<Term>> en_descriptors;

	@Before
	public void initDataEn() throws Exception {
		log.info("Reading en");
		en_descriptors = Parser.loadThesaurus(LANG.EN);
	}

	@BeforeClass
	public static void initReader() {
	}

	@Test
	public void testFr() {
		assertNotNull(fr_descriptors);
		List<Term> found = Parser.findDescriptors("Le fil de l'eau", fr_descriptors);
		assertEquals("found 2 descriptors", 2, found.size());
		log.info("Found '{}' descriptors in text", found);
		found = Parser.findDescriptors(
				"Environnement/Eau Ce jeu de données contient: Assainissement des eaux usées 1963 - 2015Stations d'épuration (en équivalents-habitants) 2000 - 2015État des masses d'eaux de surface (en %) 2009 et 2015Stations d'épuration par canton et commune 2002 - 2013--------------------------------------- Automatically synched … descriptors ",
				fr_descriptors);
		log.info("Found '{}' descriptors in text", found);
		found = Parser.findDescriptors(
				"Bruit/Kaméidi - Lärmkartierungen Le bruit, ou pollution sonore, représente pour beaucoup de citoyens une nuisance quotidienne. Les impacts sur l'être-humain peuvent être de nature physique et/ou psychique (diminution de la capacité de concentration, insomnie, stress, perte auditive, maladies cardio-vasculaires).D’autant plus, le …",
				fr_descriptors);
		log.info("Found '{}' descriptors in text", found);
		found = Parser.findDescriptors(
				"Environnement/Forêts Ce jeu de données contient: Etat phytosanitaire (en %) 1986 - 2015Superficie des forêts (en ha) par type de peuplement et par type de propriétaire 2000 - 2010Superficie des terres boisées (en ha) par région écologique 2000 - 2010Superficie forestière (en ha) 1948 - 2002Superficie forestière par …",
				fr_descriptors);
		log.info("Found '{}' descriptors in text", found);
		found = Parser.findDescriptors(
				"Ce jeu de données contient:  Heures travaillées (non-employées) par branche (NaceR2) (en 1000 heures travaillées) 2000 - 2015 Rémunération des salariés (D1) par branche (NaceR2) (en millions EUR) 2000 - 2015 Valeur ajoutée brute (B1) aux prix de base par branche (NaceR2)(en volume aux prix de l'année précédente chaînés; année de référence = 2010) (en millions EUR) 2000 - 2015 Heures travaillées total par branche (NaceR2) (en 1000 heures travaillées) 2000 - 2015 Emploi salarié (EEM) par branche (NaceR2) (en 1 000 personnes) 2000 - 2015 Consommation intermédiaire (P2) par branche (NaceR2)(en volume aux prix de l'année précédente chaînés; année de référence = 2010) (en millions EUR) 2000 - 2015 Emploi total (ETO) par branche (NaceR2) (en 1 000 personnes) 2000 - 2015 Travailleurs indépendants (ESE) par branche (NaceR2) (en 1 000 personnes) 2000 - 2015 Salaires et traitements bruts (D11) par branche (NaceR2) (en millions EUR) 2000 - 2015 Consommation intermédiaire (P2) par branche (NaceR2)(à prix courants) (en millions EUR) 2000 - 2015 Production (P1) par branche (NaceR2)(à prix courants) (en millions EUR) 2000 - 2015 Valeur ajoutée brute (B1) aux prix de base par branche (NaceR2)(à prix courants) (en millions EUR) 2000 - 2015 Production (P1) par branche (NaceR2)(en volume aux prix de l'année précédente chaînés; année de référence = 2010) (en millions EUR) 2000 - 2015 Heures travaillées (employées) par branche (NaceR2) (en 1000 heures travaillées) 2000 - 2015 Consommation de capital fixe (K1) par branche (NaceR2) (en millions EUR) 2000 - 2015 Structure de la somme des valeurs ajoutées brutes aux prix de base (NaceR2) (en % du total) 2000 - 2015 ---------------------------------------  Automatically synched from portail statistique (category Comptes nationaux/Comptes annuels - Agrégats par branche)",
				fr_descriptors);
		log.info("Found '{}' descriptors in text", found);
		found = Parser.findDescriptors(
				"'La carte de base est une carte numérique multi-échelles déduite de la BD-L-TC. Il s'agit d'une représentation du bâti, des réseaux de transport, de la végétation et de l'orographie avec un minimum de couleurs de façon à permettre une superposition d'informations thématiques supplémentaires. '",
				fr_descriptors);
		log.info("Found '{}' descriptors in text", found);
	}

}
