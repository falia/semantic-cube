package com.arhscube.gameofcode;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrawlOpenDataLu {
	static Logger log = LoggerFactory.getLogger(CrawlOpenDataLu.class);
	private static final int TIMEOUT = 5 * 1000;
	@Test
	public void crawlPageOne()throws Exception{
		URL url = new URL("https://data.public.lu/en/datasets/?page=1");
		Document doc = Jsoup.parse(url, TIMEOUT);
		log.info("Doc '{}' parsed ",doc.title());
		Elements datasets = doc.select("li[class~=dataset-result]");
		for(Element dataset : datasets){
			Element a = dataset.getElementsByTag("a").first();
			String link = a.attr("href");
			String title = a.attr("title");
			String description = dataset.getElementsByClass("result-description").first().text();
			log.info("crawled {}\n {}\n href='{}'",title, description,link);
		}
	}
}
