package jp.co.namihira.townbookweb.controller.api.dev;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.client.towerrecord.TowerRecordClient;
import jp.co.namihira.townbookweb.controller.api.AbstractApiController;

@RestController
public class EventDevApiController extends AbstractApiController {
	    
	@Autowired
	private TowerRecordClient towerRecordClient;
		
	@GetMapping("/dev/eventfetch")
	public String get() throws IOException {
		
		String result = "(";
		
		final MessageFormat mf = new MessageFormat("''{0}''");
				
		Document doc = towerRecordClient.getEvent();
		
		Elements elements = doc.getElementsByClass("textbox");
		Element element = elements.get(0);
		
		Element inner = element.getElementsByClass("inner").get(0);		
		Element title = inner.getElementsByTag("h6").get(0).getElementsByTag("a").get(0);
		
		result += toValueOnSQL(title.text());
		
		String place = "タワーレコード渋谷店";
		result += toValueOnSQL(place);
		
		result += toValueOnSQL("13");
		result += toValueOnSQL("22715");
		
		result += toValueOnSQL(title.attr("name"));
		
		result += toValueOnSQL("end");

		result += toValueOnSQL("condition");
		result += toValueOnSQL("");
		
		result += toValueOnSQL(UUID.randomUUID().toString());
		
		return result + ")";
    }
	
	private String toValueOnSQL(final String str) {
		final MessageFormat mf = new MessageFormat("''{0}'', ");
		String[] msg = {str};
		return mf.format(msg);
	}

}
