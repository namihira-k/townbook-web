package jp.co.namihira.townbookweb.client;

import java.util.List;

import org.jsoup.nodes.Document;

import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.util.CommonUtil;

public interface ServiceParser {
    
    List<EventDto> parseEvent(final Document doc);	

    default List<EventDto> parseEvent(final List<Document> docs) {
        final List<EventDto> results = CommonUtil.list();
        for (Document doc : docs) {
            final List<EventDto> result = parseEvent(doc);
            results.addAll(result);
        }
        return results;
    }

}
