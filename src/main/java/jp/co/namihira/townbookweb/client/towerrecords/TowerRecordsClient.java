package jp.co.namihira.townbookweb.client.towerrecords;

import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceClient;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class TowerRecordsClient extends ServiceClient {

    private static final List<String> EVENT_INFO_URLS = CommonUtil.list(
    		"https://tower.jp/store/event/",
    		"https://tower.jp/store/event/?page=2",
    		"https://tower.jp/store/event/?page=3",
    		"https://tower.jp/store/event/?page=4",
    		"https://tower.jp/store/event/?page=5"
    );
	
	public List<Document> getEventPages() {
		final List<Document> result = EVENT_INFO_URLS.parallelStream()
	            									 .map(url -> getPage(url))
	            									 .collect(Collectors.toList());
		return result;
	}

}
