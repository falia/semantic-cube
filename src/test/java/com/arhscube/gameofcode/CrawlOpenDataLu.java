package com.arhscube.gameofcode;

import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import com.arhscube.gameofcode.eurovoc.Term;

public class CrawlOpenDataLu extends AbstractEurovocBasedTest {
	static Logger log = LoggerFactory.getLogger(CrawlOpenDataLu.class);
	private static final int TIMEOUT = 5 * 1000;
	class Source{
		URL link;
		String format; 
	}
	class OpenDataset {
		String title;
		List<Source> sources = new ArrayList<>();
		List<Term> eurovoc = new ArrayList<>();
		public String description;
		public String author;
		public List<String> keywords = new ArrayList<>();
	}

	public List<OpenDataset> crawlAPage(int page, boolean stopQuick) throws Exception {
		List<OpenDataset> ret = new ArrayList<>();
		URL url = new URL("https://data.public.lu/en/datasets/?page=" + page);
		try {
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
				ret.add(getMore(datasetUrl));
				// Just get one for the test...
				if (stopQuick)
					break;
			} 
		} catch (SocketTimeoutException e) {
			log.error("Url {} timed out", url);
			return new ArrayList<>();
		}
		return ret;
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

	private OpenDataset getMore(URL url) throws Exception {
		OpenDataset ret = new OpenDataset();
		try {
			Document doc = Jsoup.parse(url, TIMEOUT);
			log.info("Doc '{}' parsed from '{}'", doc.title(), url);
			StringBuilder description = new StringBuilder();
			for (TextNode descr : doc.getElementsByAttributeValue("itemprop", "description").first()
					.getElementsByTag("p").first().textNodes()) {
				description.append(descr.text());
				description.append(" ");
			}
			ret.description = description.toString();
			log.info("Extracted description :'{}'", description);
			String author = doc.getElementsByAttributeValue("itemprop", "author").first().attr("title");
			ret.author=author;
			log.info("Extracted provider :'{}'", author);

			for (Element keywordElement : doc.getElementsByAttributeValue("itemprop", "keywords")) {
				String keyword = keywordElement.attr("content");
				log.info("Keyword extracted {}", keyword);
				ret.keywords.add(keyword);
			}
			
			List<Term> foundTermsFr = Parser.findDescriptors(doc.title() + " " + description.toString(),
					fr_descriptors);
			log.info("Extracted fr terms : {}", foundTermsFr);
			ret.eurovoc.addAll(foundTermsFr);
			List<Term> foundTermsEn = Parser.findDescriptors(doc.title() + " " + description.toString(),
					en_descriptors);
			ret.eurovoc.addAll(foundTermsEn);
			log.info("Extracted en terms : {}", foundTermsEn);
			List<Term> foundTermsDe = Parser.findDescriptors(doc.title() + " " + description.toString(),
					de_descriptors);
			ret.eurovoc.addAll(foundTermsDe);
			log.info("Extracted de terms : {}", foundTermsDe);
			if (foundTermsFr.size() + foundTermsEn.size() + foundTermsDe.size() == 0) {
				log.error("No descriptor found for {}", doc.title() + " " + description);
			}
			log.debug("End of Eurovoc extraction");
			for (Element dist : doc.getElementsByAttributeValue("itemprop", "distribution")) {
				Element a = dist.getElementsByAttributeValue("itemprop", "url").first();
				String href = a.attr("href");
				String format = dist.getElementsByAttribute("data-format").first().attr("data-format");
				Source src = new Source();
				src.format=format;
				src.link=new URL(href);
				ret.sources.add(src);
				log.info("Distro {} Url {}", format, href);
			}
		} catch (SocketTimeoutException e) {
			log.error("Url {} timed out", url);
			return null;
		}
		return ret;
	}
}
