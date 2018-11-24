package jp.co.namihira.townbookweb.client.hmv;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceClient;

@Service
public class HMVClient extends ServiceClient {

	public Document getEventPage(final HMVShopEnum shop) {
		return getPage(shop.getUrl());
	}
	
	
}
