package jp.co.namihira.townbookweb.client.shosen;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceParser;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class ShosenParser implements ServiceParser {
	
	public List<EventDto> parseEvent(final Document doc) {
		final List<EventDto> results = CommonUtil.list();
		
		final List<Element> eventELs = doc.getElementsByClass("borderbox");
		
		for (Element eventEL : eventELs) {
			final EventDto event = new EventDto();
			
			final Element datetimeEL = eventEL.getElementsByClass("date").first();
			final String datetimeStr = datetimeEL.text().replace("：", ":").replace("時", ":00");
			
			Pattern pDatetime = Pattern.compile("(.+?日)[^0-9]*([0-9]+:[0-9]+)[^0-9]*");
			Matcher mDatetime = pDatetime.matcher(datetimeStr);
			
			if (mDatetime.find()) {
				final ZonedDateTime date = ZonedDateTime.parse(mDatetime.group(1) + mDatetime.group(2) + " JST", DateTimeFormatter.ofPattern("yyyy年MM月dd日HH:mm zzz"));
				
				event.setStartDateTime(date.toLocalDateTime());
				event.setEndDateTime(date.plusHours(1).toLocalDateTime());
			} else {
				continue;
			}			
			
			final Element header = eventEL.getElementsByTag("a").first();
			final String name = header.text();
			event.setName(name);

			final Element shopEL = eventEL.getElementsByClass("shop").first();
			final ShosenEnum shop = ShosenEnum.getShopbyName(shopEL.text());
			
			event.setPlace(shop.getName());
			event.setPrefectureCode(shop.getPrefectureCode());
			event.setStationCode(shop.getStationCode());
			
			event.setConditions("");
			
			final String url = "https://www.shosen.co.jp" + header.attr("href");
			event.setUrl(url);
			
			final Element additionalEL = eventEL.getElementsByClass("additional").first();
			if (additionalEL != null) {
				event.setContent(additionalEL.text());				
			} else {
				event.setContent(header.text());
			}
			
			event.setIsFree(false);
			
			final String seed = datetimeEL.text() + header.text();
			event.setUuid(UUID.nameUUIDFromBytes(seed.getBytes()).toString());
			
			results.add(event);
		}
		
		return results;
	}

	
}
