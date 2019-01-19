package jp.co.namihira.townbookweb.client.hmv;

import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceClient;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class HMVClient extends ServiceClient {

    private static final List<String> EVENT_INFO_URLS = CommonUtil.list(
    		"https://www.hmv.co.jp/store/event/"
    );
	
	public List<Document> getEventPages() {
		final List<Document> result = EVENT_INFO_URLS.parallelStream()
	            									 .map(url -> getPage(url))
	            									 .collect(Collectors.toList());
		return result;
	}
	
}
