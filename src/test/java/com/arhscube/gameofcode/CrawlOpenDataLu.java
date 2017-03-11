package com.arhscube.gameofcode;

import java.net.URL;
import java.util.Random;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arhscube.gameofcode.datapublic.Crawler;

public class CrawlOpenDataLu extends AbstractEurovocBasedTest {
	static Logger log = LoggerFactory.getLogger(CrawlOpenDataLu.class);

	@Test
	public void crawlRandomPage() throws Exception {
		int page = new Random(System.currentTimeMillis()).nextInt(20) + 1;
		Crawler.crawlAPage(page, true);
	}

	@Test
	public void crawlAllPages() throws Exception {
		for (int page = 1; page <= 20; page++)
			Crawler.crawlAPage(page, false);
	}

	@Test
	public void testOneDetailedPage() throws Exception {
		Crawler.getDataset(
				new URL("https://data.public.lu/en/datasets/comptes-nationaux-comptes-annuels-agregats-par-branche/"));
	}

}
