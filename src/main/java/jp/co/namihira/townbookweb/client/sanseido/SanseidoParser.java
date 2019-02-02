package jp.co.namihira.townbookweb.client.sanseido;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceParser;
import jp.co.namihira.townbookweb.dto.EventCategoryDto;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class SanseidoParser implements ServiceParser {
	
	private static final Logger logger = LoggerFactory.getLogger(SanseidoParser.class);
	
	@Autowired
	private SanseidoClient sanseidoClient;
	
	public List<EventDto> parseEvent(final Document doc) {
		final List<EventDto> results = CommonUtil.list();
		
		final List<Element> eventELs = doc.getElementsByClass("news_blk");
		
		for (Element eventEL : eventELs) {
			final EventDto eventDto = new EventDto();
			
			final Element headerEL = eventEL.getElementsByClass("news_txt").first();
			final String title = headerEL.getElementsByTag("a").first().text();
			eventDto.setName(title);			
			
			final String url = headerEL.getElementsByTag("a").first().attr("href");
			eventDto.setUrl(url);			

			Document detailDoc = sanseidoClient.getPage(url);
			if (detailDoc == null) {
				logger.warn("detail page not found : [src]" + title + "[URL]" + url);
				logger.warn("[URL]" + url);
				continue;
			}			
			
			Element detailEL = detailDoc.getElementById("main");
			if (detailEL == null) {
				logger.warn("detail not found : [src]" + title);
				continue;
			}
			
			Element detailTable = detailEL.getElementsByClass("table").first();
			if (detailTable == null) {
				logger.warn("detailTable not found : [src]" + title);
				continue;
			}
			
			String datetimeStr = detailTable.getElementsByTag("td").first().text();
			datetimeStr = datetimeStr.replace("：", ":");
			
			Pattern p = Pattern.compile("(20[0-9]+年[0-9]+月[0-9]+日?)[^0-9]*([0-9]+:[0-9]+)[^0-9]*");
			Matcher m = p.matcher(datetimeStr);
			if (m.find()) {
				String date = m.group(1);
				String time = m.group(2);
				LocalDateTime datetime = LocalDateTime.parse(date + " " + time, DateTimeFormatter.ofPattern("yyyy年[]M月[]d日 []H:[]m"));
				eventDto.setStartDateTime(datetime);
				eventDto.setEndDateTime(datetime.plusHours(1));
			} else {
				logger.warn("datetime not match : " + datetimeStr + "[src]" + title);
				continue;
			}			
						
			final String shopName = headerEL.getElementsByClass("tag").text();
			
			final SanseidoShopEnum shop = SanseidoShopEnum.getShopbyName(shopName);
			eventDto.setPlace(shop.getName());
			eventDto.setPrefectureCode(shop.getPrefectureCode());
			eventDto.setStationCode(shop.getStationCode());
			
			eventDto.setConditions("");
			eventDto.setContent(title);
			eventDto.setIsFree(false);

            String seed = eventDto.getStartDateTime() + eventDto.getPlace() + eventDto.getName();
            eventDto.setUuid(UUID.nameUUIDFromBytes(seed.getBytes()).toString());

            EventCategoryDto eventCategoryDto = new EventCategoryDto();
            eventCategoryDto.setId(1);
            eventDto.setEventCategoryDtos(CommonUtil.list(eventCategoryDto));

            results.add(eventDto);
		}
		
		return results;
	}

}
