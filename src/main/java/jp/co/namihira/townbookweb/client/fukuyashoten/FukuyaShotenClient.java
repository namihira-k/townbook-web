package jp.co.namihira.townbookweb.client.fukuyashoten;

import java.util.List;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceClient;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class FukuyaShotenClient implements ServiceClient {

    private static final List<String> EVENT_INFO_URLS = CommonUtil.list(
    		"http://www.fukuya-shoten.jp/event/",
    		"http://www.fukuya-shoten.jp/event/page/2/");
	
	public List<Document> getEventPages() {
		return getPages(EVENT_INFO_URLS);
	}
	
}
