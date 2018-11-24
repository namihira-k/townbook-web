package jp.co.namihira.townbookweb.controller.api.dev;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.client.hmv.HMVClient;
import jp.co.namihira.townbookweb.client.hmv.HMVShopEnum;
import jp.co.namihira.townbookweb.client.towerrecords.TowerRecordsClient;
import jp.co.namihira.townbookweb.client.towerrecords.TowerRecordsShopEnum;
import jp.co.namihira.townbookweb.controller.api.AbstractApiController;

@RestController
public class EventDevApiController extends AbstractApiController {
	    
	@Autowired
	private HMVClient hmvClient;
	
	@Autowired
	private TowerRecordsClient towerRecordClient;
		
	
	@GetMapping("/dev/eventfetch-hmv")
	public String getHMVData() {
		String result = "";
		
		int id = 200;
		
		for (HMVShopEnum shop : HMVShopEnum.values()) {
			Document doc = hmvClient.getEventPage(shop);
			
			Element eventInfo = doc.getElementsByClass("stEventBox").get(0);
			Elements events = eventInfo.getElementsByTag("li");

			for (int i = 0; i < events.size(); i++, id++) {
				String str = String.valueOf(id) + ",";
				
				Element event = events.get(i);
				String title = event.getElementsByTag("a").first().text();
				str += toValueOnSQL(title);

				String place = shop.getName();
				str += toValueOnSQL(place);
				
				str += toValueOnSQL(shop.getPrefectureCode());
				str += toValueOnSQL(shop.getStationCode());
				
				String datetime = event.text();
				Pattern p = Pattern.compile("(20[0-9]+/[0-9]+/[0-9]+) ([0-9]+):([0-9]+)");
				Matcher m = p.matcher(datetime);
				if (m.find()) {
					str += toValueOnSQL(m.group().replace("/", "-") + ":00");
					str += toValueOnSQL(m.group(1).replace("/", "-") + " " + (Integer.parseInt(m.group(2))+1) + ":" + m.group(3) + ":00");
				}	

				// condition
				str += toValueOnSQL("");

				String url = "https://www.hmv.co.jp" + event.getElementsByTag("a").first().attr("href");
				str += toValueOnSQL(url);

				str += toValueOnSQL(title);
				
				Document info = hmvClient.getPage(url);
				Elements contents = info.getElementsByClass("eventInfoText");
				final List<String> freeKeywords = HMVShopEnum.getKeywordsOfFree();
				Boolean isFree = freeKeywords.parallelStream().anyMatch(f -> contents.text().contains(f));
				str += String.valueOf(isFree) + ",";

				
				String seed = datetime + title;
				str += toValueOnSQL(UUID.nameUUIDFromBytes(seed.getBytes()).toString());
				
				result += "(" + str.substring(0, str.length()-2) + "),";
			}			
		}
		
		return result.substring(0, result.length()-1) + ";";		
	}
	
	@GetMapping("/dev/eventfetch-tr")
	public String getDatas() {
		String result = "";
		
		int id = 100;

		for (TowerRecordsShopEnum shop : TowerRecordsShopEnum.values()) {
			Document doc = towerRecordClient.getEventPage(shop);
			Elements tbodies = doc.getElementsByTag("tr");
			
			for (int i = 1; i < tbodies.size(); i++, id++) {
				String str = String.valueOf(id) + ",";
				
				Elements infos = tbodies.get(i).getElementsByTag("td");

				Element event = infos.get(2).getElementsByTag("a").first();
				String title = event.text();
				str += toValueOnSQL(title);
				
				String place = shop.getName();
				str += toValueOnSQL(place);

				str += toValueOnSQL(shop.getPrefectureCode());
				str += toValueOnSQL(shop.getStationCode());
				
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

				String url = "https://tower.jp" + event.attr("href");
				str += toValueOnSQL(url);

				String content = infos.get(3).text();
				str += toValueOnSQL(content);

				Document info = towerRecordClient.getPage(url);
				Elements contents = info.getElementsByClass("storeInfo-List");
				final List<String> freeKeywords = TowerRecordsShopEnum.getKeywordsOfFree();
				Boolean isFree = freeKeywords.parallelStream().anyMatch(f -> contents.text().contains(f));
				str += String.valueOf(isFree) + ",";
				
				String seed = date + time + title;
				str += toValueOnSQL(UUID.nameUUIDFromBytes(seed.getBytes()).toString());
				
				result += "(" + str.substring(0, str.length()-2) + "),";
			}
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
