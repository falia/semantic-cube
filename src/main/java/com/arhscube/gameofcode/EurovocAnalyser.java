package com.arhscube.gameofcode;

import com.arhscube.gameofcode.datapublic.OpenDataset;
import com.arhscube.gameofcode.eurovoc.Parser;
import com.arhscube.gameofcode.eurovoc.Parser.LANG;

public class EurovocAnalyser {
	public static void analyse(OpenDataset ds, LANG... langs) {
		for (LANG lg : langs) {
			ds.eurovoc.addAll(Parser.findDescriptors(ds.title + " " + ds.description, Parser.loadThesaurus(lg)));
		}
	}
}
