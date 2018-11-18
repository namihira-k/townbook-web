package jp.co.namihira.townbookweb.controller.api.dev;

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
	public String getDatas() {
		String result = "";
		
		Document doc = towerRecordClient.getEventPage();		
		Elements tbodies = doc.getElementsByTag("tr");
		
		int id = 100;
		
		for (int i = 1; i < tbodies.size(); i++, id++) {
			String str = String.valueOf(id) + ",";
			
			Elements infos = tbodies.get(i).getElementsByTag("td");

			Element event = infos.get(2).getElementsByTag("a").first();
			String title = event.text();
			str += toValueOnSQL(title);
			
			String place = "タワーレコード渋谷店";
			str += toValueOnSQL(place);

			str += toValueOnSQL("13");
			str += toValueOnSQL("22715");
			
			String date = infos.get(0).text().replace("/", "-");
			String time = infos.get(1).text();
			
			str += toValueOnSQL(date + " " + time + ":00");
			Pattern p = Pattern.compile("([0-9]+):([0-9]+)");
			Matcher m = p.matcher(time);
			if (m.find()) {
				str += toValueOnSQL(date + " " + (Integer.parseInt(m.group(1))+1) + ":" + m.group(2) + ":00");				
			}
			
			// condition
			str += toValueOnSQL("");

			String url = event.attr("href");
			str += toValueOnSQL("https://tower.jp" + url);

			String content = infos.get(3).text();
			str += toValueOnSQL(content);			

			String seed = date + time + title;
			str += toValueOnSQL(UUID.nameUUIDFromBytes(seed.getBytes()).toString());
			
			result += "(" + str.substring(0, str.length()-2) + "),";						
		}
		
		return result.substring(0, result.length()-1) + ";";
	}
		
	private String toValueOnSQL(String str) {
		final MessageFormat mf = new MessageFormat("''{0}'', ");
		str = str.replace("'", "");		
		String[] msg = {str};
		return mf.format(msg);
	}

}
