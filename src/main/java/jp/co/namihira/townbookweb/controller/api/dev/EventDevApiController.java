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

import jp.co.namihira.townbookweb.client.fukuyashoten.FukuyaShotenClient;
import jp.co.namihira.townbookweb.client.fukuyashoten.FukuyaShotenEnum;
import jp.co.namihira.townbookweb.client.hmv.HMVClient;
import jp.co.namihira.townbookweb.client.hmv.HMVShopEnum;
import jp.co.namihira.townbookweb.client.shosen.ShosenClient;
import jp.co.namihira.townbookweb.client.shosen.ShosenParser;
import jp.co.namihira.townbookweb.client.towerrecords.TowerRecordsClient;
import jp.co.namihira.townbookweb.client.towerrecords.TowerRecordsShopEnum;
import jp.co.namihira.townbookweb.controller.api.AbstractApiController;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.util.CommonUtil;

@RestController
public class EventDevApiController extends AbstractApiController {
	    
	@Autowired
	private ShosenClient shosenClient;	
	@Autowired
	private FukuyaShotenClient fukuyaShotenClient;
	@Autowired
	private HMVClient hmvClient;
	@Autowired
	private TowerRecordsClient towerRecordClient;
		
	@GetMapping("/dev/eventfetch-shosen")
	public String getShosenData() {
		int id = 700;
		
		final List<Document> docs = shosenClient.getEventPages();
		final List<EventDto> events = ShosenParser.parseEvent(docs);
		return EventSQLBuilder.build(id, events);
	}
	
	@GetMapping("/dev/eventfetch-fs")
	public String getFukuyaShotenData() {
		String result = "";
		
		int id = 500;
		
		final List<Document> docs = fukuyaShotenClient.getEventPages();
		
		for (Document doc : docs) {
			List<Element> events = doc.getElementsByClass("topItem");
			
			for (Element event : events) {
				String str = String.valueOf(id) + ",";
				
				String header = event.getElementsByTag("h3").text();
				
				Pattern pHeader = Pattern.compile("(.+) \\| (.+)");
				Matcher mHeader = pHeader.matcher(header);
				if (mHeader.find()) {
					final String title = mHeader.group(1);
					str += toValueOnSQL(title);
					final String shopName = mHeader.group(2);
					
					FukuyaShotenEnum shop = FukuyaShotenEnum.getShopbyName(shopName);
					
					str += toValueOnSQL(shop.getName());					
					str += toValueOnSQL(shop.getPrefectureCode());
					str += toValueOnSQL(shop.getStationCode());
				}
				
				String datetime = event.getElementsByClass("timest").get(0).text();
				datetime = datetime.replace("（", "(").replace("）", ")").replace("：", ":").replace(" ", "");
				Pattern pTime = Pattern.compile("([0-9]+)年([0-9]+)月([0-9]+)日\\(.\\)([0-9]+):([0-9]+)～");
				Matcher mTime = pTime.matcher(datetime);

				if (mTime.find()) {
					String date = mTime.group(1) + "-" + mTime.group(2) + "-" + mTime.group(3);
					str += toValueOnSQL(date + " " + mTime.group(4) + ":" + mTime.group(5) + ":00");
					str += toValueOnSQL(date + " " + (Integer.parseInt(mTime.group(4))+1) + ":" + mTime.group(5) + ":00");					
				}

				// condition
				str += toValueOnSQL("");

				String url = event.getElementsByTag("a").first().attr("href");
				str += toValueOnSQL(url);

				// content
				str += toValueOnSQL(header);
				
				// isFree
				str += String.valueOf(false) + ",";
				
				String seed = datetime + header;
				str += toValueOnSQL(UUID.nameUUIDFromBytes(seed.getBytes()).toString());
				
				result += "(" + str.substring(0, str.length()-2) + "),";
				
				id++;
			}			
		}
		
		return result.substring(0, result.length()-1) + ";";
	}
	
	@GetMapping("/dev/eventfetch-hmv")
	public String getHMVData() {
		String result = "";
		
		int id = 300;
		
		for (HMVShopEnum shop : HMVShopEnum.values()) {
			Document doc = hmvClient.getEventPage(shop);
			
			if (!doc.text().contains("ストアイベント情報")) {
				break;
			}
			
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

				// content
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
				
				if (CommonUtil.isNotEmpty(time)) {
					str += toValueOnSQL(date + " " + time + ":00");					
				} else {
					str += toValueOnSQL("");
				}
				
				Pattern p = Pattern.compile("([0-9]+):([0-9]+)");
				Matcher m = p.matcher(time);
				if (m.find()) {
					str += toValueOnSQL(date + " " + (Integer.parseInt(m.group(1))+1) + ":" + m.group(2) + ":00");
				} else {
					str += toValueOnSQL("");					
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
