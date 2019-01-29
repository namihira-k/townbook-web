package jp.co.namihira.townbookweb.client.towerrecords;

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
import jp.co.namihira.townbookweb.dto.EventCategoryDto;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class TowerRecordsParser implements ServiceParser {

	@Autowired
	private TowerRecordsClient towerRecordsClient;	
		
	public List<EventDto> parseEvent(final Document doc) {
		final List<EventDto> results = CommonUtil.list();
		
		Elements tbodies = doc.getElementsByTag("tr");
			
		for (int i = 1; i < tbodies.size(); i++) {
			final EventDto dto = new EventDto();
			Elements infos = tbodies.get(i).getElementsByTag("td");

			Element eventEL = infos.get(2).getElementsByTag("a").first();
			String title = eventEL.text();
			dto.setName(title);
				
			Element shopEL = infos.get(4).getElementsByTag("a").first();
			
			final TowerRecordsShopEnum shop = TowerRecordsShopEnum.getShopbyName(shopEL.text());
			if (shop == null) {
				continue;
			}
			dto.setPlace(shop.getName());
			dto.setPrefectureCode(shop.getPrefectureCode());
			dto.setStationCode(shop.getStationCode());
				
			String date = infos.get(0).text().replace("/", "-");
			String time = infos.get(1).text();
				
			if (CommonUtil.isNotEmpty(time)) {
				dto.setStartDateTime(LocalDateTime.parse(date + " " + time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
			} else {
				continue;
			}
				
			Pattern p = Pattern.compile("([0-9]+):([0-9]+)");
			Matcher m = p.matcher(time);
			if (m.find()) {
				dto.setEndDateTime(LocalDateTime.parse(date + " " + (Integer.parseInt(m.group(1))+1) + ":" + m.group(2), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));					
			} else {
				continue;
			}
				
			// condition
			dto.setConditions("");

			String url = "https://tower.jp" + eventEL.attr("href");
			dto.setUrl(url);

			String content = infos.get(3).text();
			dto.setContent(content);
			
			Document detailEL = towerRecordsClient.getPage(url);
			if (detailEL != null) {
                Elements contents = detailEL.getElementsByClass("storeInfo-List");
                final List<String> freeKeywords = TowerRecordsConsts.getKeywordsOfFree();
                Boolean isFree = freeKeywords.parallelStream().anyMatch(f -> contents.text().contains(f));
                dto.setIsFree(isFree);
            } else {
                dto.setIsFree(false);
            }

			String seed = date + time + title;
			dto.setUuid(UUID.nameUUIDFromBytes(seed.getBytes()).toString());

            EventCategoryDto eventCategoryDto = new EventCategoryDto();
            eventCategoryDto.setId(2);
            dto.setEventCategoryDtos(CommonUtil.list(eventCategoryDto));
			
			results.add(dto);
		}
		
		return results;
	}

	
}
