package jp.co.namihira.townbookweb.client.towerrecords;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class ServiceClient {

	public Document getPage(final String url) {
		Document doc = null;;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;		
	}
	
	
}
