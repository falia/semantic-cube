package com.arhscube.gameofcode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.arhscube.gameofcode.datapublic.Crawler;
import com.arhscube.gameofcode.datapublic.OpenDataset;
import com.arhscube.gameofcode.datapublic.Source;
import com.arhscube.gameofcode.eurovoc.Parser.LANG;

import model.DataSet;
import model.Distribution;

public class TestIntegrationDataset {
	@Test
	public void importFromWebSite() throws Exception {
		DateFormat df = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:SS.SSSSSS");
		for (OpenDataset dataset : Crawler.crawlAPage(1, true)) {
			EurovocAnalyser.analyse(dataset, LANG.EN, LANG.FR, LANG.DE);
			DataSet rdfDataset = new DataSet();
			rdfDataset.setDescription(dataset.description);
			rdfDataset.setTitle(dataset.title);
			rdfDataset.setPublisher(dataset.author);
			rdfDataset.setTheme("#TODO#");

			List<Distribution> dists = new ArrayList<>();
			for (Source src : dataset.sources) {
				Distribution dist = new Distribution(src.description, src.link.toString(), src.format,
						new Long(src.size), df.parse(src.createdOn), df.parse(src.modifiedOn));
			}
			rdfDataset.setDistributionList(dists);
		}
	}
}
