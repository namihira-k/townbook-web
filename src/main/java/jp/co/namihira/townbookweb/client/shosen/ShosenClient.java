package jp.co.namihira.townbookweb.client.shosen;

import java.util.List;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceClient;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class ShosenClient implements ServiceClient {
		
	
    private static final List<String> EVENT_INFO_URLS = CommonUtil.list(
    		"https://www.shosen.co.jp/event/?sortable=monthly",
    		"https://www.shosen.co.jp/event/page/2/?sortable=monthly");
	
	public List<Document> getEventPages() {
		return getPages(EVENT_INFO_URLS);
	}

}
