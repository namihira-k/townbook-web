package jp.co.namihira.townbookweb.client.towerrecords;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class TowerRecordsClient {

	public Document getEventPage(final TowerRecordsShopEnum shop) {
		Document doc = null;;
		try {
			doc = Jsoup.connect(shop.getUrl()).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	
}
