package com.arhscube.gameofcode;

import java.util.ArrayList;
import java.util.List;

import com.arhscube.gameofcode.datapublic.OpenDataset;
import com.arhscube.gameofcode.eurovoc.Parser;
import com.arhscube.gameofcode.eurovoc.Parser.LANG;
import com.arhscube.gameofcode.eurovoc.Term;

public class EurovocAnalyser {
	public static void analyse(OpenDataset ds, LANG... langs) {
		for (LANG lg : langs) {
			for (Term t : Parser.findDescriptors(ds.title + " " + ds.description, Parser.loadThesaurus(lg))) {
				ds.eurovoc.add(t.getId());
			}
		}
	}

	public static List<Term> analyse(String text, LANG... langs) {
		List<Term> analysed = new ArrayList<>();
		for (LANG lg : langs) {
			analysed.addAll(Parser.findDescriptors(text, Parser.loadThesaurus(lg)));
		}
		return analysed;
	}
}
