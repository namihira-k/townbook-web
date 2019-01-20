package jp.co.namihira.townbookweb.client.hmv;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceParser;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class HMVParser implements ServiceParser {
	
	@Autowired
	private HMVClient hmvClient;
	
	public List<EventDto> parseEvent(final Document doc) {
		final List<EventDto> results = CommonUtil.list();
		
		final Element eventListEL = doc.getElementById("dateIdx");
		final List<Element> eventELs = eventListEL.getElementsByClass("idxList");
		

		for (Element eventEL : eventELs) {
			EventDto eventDto = new EventDto();
			
			final String person = eventEL.getElementsByTag("dt").first().text();
			final String title = eventEL.getElementsByClass("eventTitle").first().text();			
			eventDto.setName(person + " " + title);
			
			final String shopName = eventEL.getElementsByClass("eventLocation").first().text();
			final HMVShopEnum shop = HMVShopEnum.getShopbyName(shopName);
			eventDto.setPlace(shop.getName());
			eventDto.setPrefectureCode(shop.getPrefectureCode());
			eventDto.setStationCode(shop.getStationCode());
			
			eventDto.setConditions("");

			final String url = "https://www.hmv.co.jp" + eventEL.getElementsByTag("a").first().attr("href");
			eventDto.setUrl(url);

			eventDto.setContent(person + " " + title);
				
			Document detailEL = hmvClient.getPage(url);
			Elements contents = detailEL.getElementsByClass("eventInfoText");
			final List<String> freeKeywords = HMVConsts.getKeywordsOfFree();
			Boolean isFree = freeKeywords.parallelStream().anyMatch(f -> contents.text().contains(f));
			eventDto.setIsFree(isFree);

			
			final List<LocalDateTime> startDateTimes = CommonUtil.list();
			final String datetimeStrs = eventEL.getElementsByTag("dd").get(1).text();
			final List<String> splitedDatetime = CommonUtil.list(datetimeStrs.split("\\|"));
			
			final String datetimeStr = splitedDatetime.get(0);
			
			Pattern p = Pattern.compile("(20[0-9]+/[0-9]+/[0-9]+)[^0-9]*([0-9]+:[0-9]+)");
			Matcher m = p.matcher(datetimeStr);
			String date = null;
			if (m.find()) {
				date = m.group(1);
				String time = m.group(2);
				LocalDateTime datetime = LocalDateTime.parse(date + " " + time, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
				startDateTimes.add(datetime);
			} else {
				continue;
			}

			for (int i = 1; i < splitedDatetime.size(); i++) {
				String nextTime = splitedDatetime.get(i).trim();
				LocalDateTime datetime = LocalDateTime.parse(date + " " + nextTime, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
				startDateTimes.add(datetime);
			}
			
			for (LocalDateTime sdt : startDateTimes) {
				eventDto.setStartDateTime(sdt);
				eventDto.setEndDateTime(sdt.plusHours(1));

				String seed = sdt + person + title;
				eventDto.setUuid(UUID.nameUUIDFromBytes(seed.getBytes()).toString());
				
				results.add(eventDto);
				
				eventDto = eventDto.clone();
			}
		}

		return results;
	}
	
}
