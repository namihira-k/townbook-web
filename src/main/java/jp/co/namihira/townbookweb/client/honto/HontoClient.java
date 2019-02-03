package jp.co.namihira.townbookweb.client.honto;

import java.util.List;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceClient;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class HontoClient implements ServiceClient {

    private static final List<String> EVENT_INFO_URLS = CommonUtil.list(
            "https://honto.jp/store/news_14HB300_6115678_081.html",
            "https://honto.jp/store/news_14HB300_6115678_082.html",
            "https://honto.jp/store/news_14HB300_6115678_083.html",
            "https://honto.jp/store/news_14HB300_6115678_084.html",
            "https://honto.jp/store/news_14HB300_6115678_085.html",
            "https://honto.jp/store/news_14HB300_6115678_086.html",            
            "https://honto.jp/store/news_14HB300_6115678_087.html",
            "https://honto.jp/store/news_14HB300_6115678_088.html",
            "https://honto.jp/store/news_14HB300_6115678_089.html"            
    );

    public List<Document> getEventPages() {
        return getPages(EVENT_INFO_URLS);
    }

}
