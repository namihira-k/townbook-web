package jp.co.namihira.townbookweb.client.shosen;

import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceClient;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class ShosenClient extends ServiceClient {
		
	
    private static final List<String> EVENT_INFO_URLS = CommonUtil.list("https://www.shosen.co.jp/event/?sortable=monthly", "https://www.shosen.co.jp/event/page/2/?sortable=monthly");
	
	public List<Document> getEventPages() {
		final List<Document> result = EVENT_INFO_URLS.parallelStream()
	            									 .map(url -> getPage(url))
	            									 .collect(Collectors.toList());
		return result;
	}

}
