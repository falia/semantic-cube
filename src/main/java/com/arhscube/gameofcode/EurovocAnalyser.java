package com.arhscube.gameofcode;

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
}
