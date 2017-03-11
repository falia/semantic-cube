package com.arhscube.gameofcode.datapublic;

import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Crawler {
	private static final int TIMEOUT = 5 * 1000;
	static Logger log = LoggerFactory.getLogger(Crawler.class);

	public static List<OpenDataset> crawlAPage(int page, boolean stopQuick) throws Exception {
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
				ret.add(getDataset(datasetUrl));
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

	public static OpenDataset getDataset(URL url) throws Exception {
		OpenDataset ret = new OpenDataset();
		ret.origin = url;
		try {
			Document doc = Jsoup.parse(url, TIMEOUT);
			ret.title = doc.title();
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
			ret.author = author;
			log.info("Extracted provider :'{}'", author);

			for (Element keywordElement : doc.getElementsByAttributeValue("itemprop", "keywords")) {
				String keyword = keywordElement.attr("content");
				log.info("Keyword extracted {}", keyword);
				ret.keywords.add(keyword);
			}

			log.debug("End of Eurovoc extraction");
			for (Element dist : doc.getElementsByAttributeValue("itemprop", "distribution")) {
				Element a = dist.getElementsByAttributeValue("itemprop", "url").first();
				String href = a.attr("href");
				Source src = new Source();
				src.format = getItemPropContent(dist, "encodingFormat");
				src.description = getItemPropContent(dist, "description");
				src.createdOn = getItemPropContent(dist, "dateCreated");
				src.size = getItemPropContent(dist, "contentSize");
				src.modifiedOn = getItemPropContent(dist, "dateModified");
				src.link = new URL(href);
				ret.sources.add(src);
				log.info("Distro {} ", src);
			}
		} catch (SocketTimeoutException e) {
			log.error("Url {} timed out", url);
			return null;
		}
		return ret;
	}

	private static String getItemPropContent(Element el, String property) {
		String ret = null;
		Elements els = el.getElementsByAttributeValue("itemprop", property);
		if (els != null && els.size() > 0) {
			return els.first().attr("content");
		}
		return ret;
	}
}
