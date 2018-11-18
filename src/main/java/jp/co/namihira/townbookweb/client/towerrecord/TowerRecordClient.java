package jp.co.namihira.townbookweb.client.towerrecord;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class TowerRecordClient {

	private static final String URL_EVENT = "https://tower.jp/store/kanto/Shibuya/event";
		
	public Document getEventPage() {
		Document doc = null;;
		try {
			doc = Jsoup.connect(URL_EVENT).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	
}
