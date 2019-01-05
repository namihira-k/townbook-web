package jp.co.namihira.townbookweb.client.kinokuniya;

import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceClient;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class KinokuniyaClient extends ServiceClient {
		
    private static final List<String> EVENT_INFO_URLS = CommonUtil.list(
    		"https://www.kinokuniya.co.jp/c/event_special/event/session/",
    		"https://www.kinokuniya.co.jp/c/event_special/event/session/index-2.html",
    		"https://www.kinokuniya.co.jp/c/event_special/event/talkevent/",
    		"https://www.kinokuniya.co.jp/c/event_special/event/talkevent/index-2.html"
    );
	
	public List<Document> getEventPages() {
		final List<Document> result = EVENT_INFO_URLS.parallelStream()
	            									 .map(url -> getPage(url))
	            									 .collect(Collectors.toList());
		return result;
	}

}
