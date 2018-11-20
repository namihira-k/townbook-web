package jp.co.namihira.townbookweb.client.hmv;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class HMVClient {

	public Document getEventPage(final HMVShopEnum shop) {
		Document doc = null;;
		try {
			doc = Jsoup.connect(shop.getUrl()).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	
}
