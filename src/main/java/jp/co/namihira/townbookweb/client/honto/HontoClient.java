package jp.co.namihira.townbookweb.client.honto;

import java.util.List;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceClient;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class HontoClient implements ServiceClient {

    private static final List<String> EVENT_INFO_URLS = CommonUtil.list(
            "https://honto.jp/store/news_6115678_081.html"
    );

    public List<Document> getEventPages() {
        return getPages(EVENT_INFO_URLS);
    }

}
