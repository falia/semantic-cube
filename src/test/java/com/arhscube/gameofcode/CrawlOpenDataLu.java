package com.arhscube.gameofcode;

import java.net.URL;
import java.util.Random;

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
	public void crawlARandomPage()throws Exception{
		int page=new Random(System.currentTimeMillis()).nextInt(20)+1;
		URL url = new URL("https://data.public.lu/en/datasets/?page="+page);
		Document doc = Jsoup.parse(url, TIMEOUT);
		log.info("Doc '{}' parsed from '{}'",doc.title(),url);
		Elements datasets = doc.select("li[class~=dataset-result]");
		for(Element dataset : datasets){
			Element a = dataset.getElementsByTag("a").first();
			String link = a.attr("href");
			URL datasetUrl = new URL(url,link);
			String title = a.attr("title");
			String description = dataset.getElementsByClass("result-description").first().text();
			log.info("crawled {}\n {}\n href='{}'",title, description,link);
			log.info("Could get more from '{}'",datasetUrl);
		}
	}
}
