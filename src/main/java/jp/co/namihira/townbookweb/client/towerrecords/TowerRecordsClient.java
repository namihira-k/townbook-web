package jp.co.namihira.townbookweb.client.towerrecords;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class TowerRecordsClient extends ServiceClient {

	public Document getEventPage(final TowerRecordsShopEnum shop) {
		return getPage(shop.getUrl());
	}		
	
}
