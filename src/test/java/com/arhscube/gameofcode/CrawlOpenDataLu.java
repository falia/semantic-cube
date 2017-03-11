package com.arhscube.gameofcode;

import java.net.URL;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arhscube.gameofcode.eurovoc.Parser;

public class CrawlOpenDataLu extends AbstractEurovocBasedTest {
	static Logger log = LoggerFactory.getLogger(CrawlOpenDataLu.class);
	private static final int TIMEOUT = 5 * 1000;

	public void crawlAPage(int page, boolean stopQuick) throws Exception {
		URL url = new URL("https://data.public.lu/en/datasets/?page=" + page);
		Document doc = Jsoup.parse(url, TIMEOUT);
		log.info("Doc '{}' parsed from '{}'", doc.title(), url);
		Elements datasets = doc.select("li[class~=dataset-result]");
		for (Element dataset : datasets) {
			Element a = dataset.getElementsByTag("a").first();
			String link = a.attr("href");
			URL datasetUrl = new URL(url, link);
			String title = a.attr("title");
			String description = dataset.getElementsByClass("result-description").first().text();
			log.info("crawled {}\n {}\n href='{}'", title, description, link);
			log.info("Could get more from '{}'", datasetUrl);
			getMore(datasetUrl);
			// Just get one for the test...
			if (stopQuick)
				break;
		}
	}

	@Test
	public void crawlRandomPage() throws Exception {
		int page = new Random(System.currentTimeMillis()).nextInt(20) + 1;
		crawlAPage(page, true);
	}

	@Test
	public void crawlAllPages() throws Exception {
		for (int page = 1; page <= 20; page++)
			crawlAPage(page, false);
	}

	@Test
	public void testOneDetailedPage() throws Exception {
		getMore(new URL("https://data.public.lu/en/datasets/comptes-nationaux-comptes-annuels-agregats-par-branche/"));
	}

	private void getMore(URL url) throws Exception {
		Document doc = Jsoup.parse(url, TIMEOUT);
		log.info("Doc '{}' parsed from '{}'", doc.title(), url);
		StringBuilder description = new StringBuilder();
		for (TextNode descr : doc.getElementsByAttributeValue("itemprop", "description").first().getElementsByTag("p")
				.first().textNodes()) {
			description.append(descr.text());
			description.append(" ");
		}
		log.info("Extracted description :'{}'", description);
		log.info("Extracted fr terms : {}", Parser.findDescriptors(description.toString(), fr_descriptors));
		log.info("Extracted en terms : {}", Parser.findDescriptors(description.toString(), en_descriptors));
		log.info("Extracted de terms : {}", Parser.findDescriptors(description.toString(), de_descriptors));
		for (Element dist : doc.getElementsByAttributeValue("itemprop", "distribution")) {
			Element a = dist.getElementsByAttributeValue("itemprop", "url").first();
			String href = a.attr("href");
			String format = dist.getElementsByAttribute("data-format").first().attr("data-format");
			log.info("Distro {} Url {}", format, href);

		}
	}
}
