package jp.co.namihira.townbookweb.client;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public interface ServiceClient {

	public default List<Document> getPages(final List<String> urls) {
		final List<Document> result = urls.parallelStream()
				                          .map(url -> getPage(url))
				                          .collect(Collectors.toList());
		return result;
	}
	
	public default Document getPage(final String url) {
		Document doc = null;;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;		
	}
	
	public List<Document> getEventPages();
	
}
