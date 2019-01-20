package jp.co.namihira.townbookweb.client.sanseido;

import java.util.List;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceClient;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class SanseidoClient implements ServiceClient {
	
    private static final List<String> EVENT_INFO_URLS = CommonUtil.list(
    		"https://www.books-sanseido.co.jp/events?narabi=sort2");
	
	public List<Document> getEventPages() {
		return getPages(EVENT_INFO_URLS);
	}

}
