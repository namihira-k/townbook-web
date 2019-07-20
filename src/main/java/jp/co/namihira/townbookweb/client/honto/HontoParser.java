package jp.co.namihira.townbookweb.client.honto;

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
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceParser;
import jp.co.namihira.townbookweb.dto.EventCategoryDto;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class HontoParser implements ServiceParser {
    
    private static final Logger logger = LoggerFactory.getLogger(HontoParser.class);

    public List<EventDto> parseEvent(final Document doc) {
        final List<EventDto> results = CommonUtil.list();

        final List<Element> eventELs = doc.getElementsByClass("stBoxLine01");

        for (Element eventEL : eventELs) {
            EventDto eventDto = new EventDto();
        
            final Element contentEL= eventEL.getElementsByClass("stContents").get(0);
            
            final String name = contentEL.getElementsByTag("a").first().text();
            eventDto.setName(name);

            final String shopName = contentEL.getElementsByClass("stMarginB05").first().text();
            if (shopName.contains(MaruzenShopEnum.SHOP_NAME)) {
                eventDto.setOrgCode(MaruzenShopEnum.ORG_CODE);
                final MaruzenShopEnum shop = MaruzenShopEnum.getShopbyName(shopName);
                eventDto.setPlace(shop.getName());
                eventDto.setPrefectureCode(shop.getPrefectureCode());
                eventDto.setStationCode(shop.getStationCode());
            } else if (shopName.contains(JunkudoShopEnum.SHOP_NAME)) {
                eventDto.setOrgCode(JunkudoShopEnum.ORG_CODE);
                final JunkudoShopEnum shop = JunkudoShopEnum.getShopbyName(shopName);
                eventDto.setPlace(shop.getName());
                eventDto.setPrefectureCode(shop.getPrefectureCode());
                eventDto.setStationCode(shop.getStationCode());
            } else {
                continue;
            }

            List<Element> datetimeELs = contentEL.getElementsByTag("p");
            if (datetimeELs.size() < 2) {
                logger.info("no datetime info" +  "[src]" + name);
                continue;
            }
                    
            String datetimeStr = datetimeELs.get(1).text();
            datetimeStr = datetimeStr.replace("（", "(").replace("）", ")").replace(" ", "");
            Pattern pTime = Pattern.compile("([0-9]+年[0-9]+月[0-9]+日?)[^0-9]*([0-9]+:[0-9]+)[^0-9]*");
            Matcher mTime = pTime.matcher(datetimeStr);

            if (mTime.find()) {
                String date = mTime.group(1);
                String time = mTime.group(2);
                LocalDateTime datetime = LocalDateTime.parse(date + " " + time,
                        DateTimeFormatter.ofPattern("yyyy年[]M月[]d日 []H:[]m"));
                eventDto.setStartDateTime(datetime);
                eventDto.setEndDateTime(datetime.plusHours(1));
            } else {
                logger.warn("datetime not match : " + datetimeStr + "[src]" + name);
                continue;
            }
            
            eventDto.setConditions("");

            final String url = contentEL.getElementsByTag("a").first().attr("href");
            eventDto.setUrl(url);

            eventDto.setContent(name);
            eventDto.setIsFree(false);

            EventCategoryDto eventCategoryDto = new EventCategoryDto();
            eventCategoryDto.setId(1);
            eventDto.setEventCategoryDtos(CommonUtil.list(eventCategoryDto));            
            
            String seed = eventDto.getStartDateTime() + eventDto.getPlace() + eventDto.getName();
            eventDto.setUuid(UUID.nameUUIDFromBytes(seed.getBytes()).toString());
                
            results.add(eventDto);
        }

        return results;
    }

}
