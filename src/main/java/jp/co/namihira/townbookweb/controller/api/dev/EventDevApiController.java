package jp.co.namihira.townbookweb.controller.api.dev;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String result = "";
		
		Document doc = towerRecordClient.getEvent();
		Elements elements = doc.getElementsByClass("textbox");
		
		int id = 100;
		
		for (int i = 0; i < elements.size(); i++, id++) {
			String str = String.valueOf(id) + ",";

			Element element = elements.get(i);
			Element inner = element.getElementsByClass("inner").get(0);		
			Element title = inner.getElementsByTag("h6").get(0).getElementsByTag("a").get(0);
			
			str += toValueOnSQL(title.text());
			
			String place = "タワーレコード渋谷店";
			str += toValueOnSQL(place);
			
			str += toValueOnSQL("13");
			str += toValueOnSQL("22715");
			
			final String date = title.attr("name");
			
			String content = inner.getElementsByClass("content_title").get(0).text();
			Pattern p = Pattern.compile("([0-9]+).+?([0-9]+)");
			Matcher m = p.matcher(content);
			if (m.find()) {
				str += toValueOnSQL(date + " " + m.group(1) + ":" + m.group(2) + ":00");
				str += toValueOnSQL(date + " " + (Integer.parseInt(m.group(1))+1) + ":" + m.group(2) + ":00");
			}
			
			// condition
			str += toValueOnSQL("");
			
			String url = title.attr("href");
			str += toValueOnSQL(url);

			// content
			str += toValueOnSQL("");			
			
			str += toValueOnSQL(UUID.randomUUID().toString());
			
			result += "(" + str.substring(0, str.length()-2) + "),";			
		}
		
		return result.substring(0, result.length()-1) + ";";
    }
	
	private String toValueOnSQL(final String str) {
		final MessageFormat mf = new MessageFormat("''{0}'', ");
		String[] msg = {str};
		return mf.format(msg);
	}

}
