package jp.co.namihira.townbookweb.client.kinokuniya;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ServiceParser;
import jp.co.namihira.townbookweb.consts.EventCategoryEnum;
import jp.co.namihira.townbookweb.dto.EventCategoryDto;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.util.CommonUtil;
import jp.co.namihira.townbookweb.util.DateTimeUtil;

@Service
public class KinokuniyaParser implements ServiceParser {

    @Autowired
    private KinokuniyaClient kinokuniyaClient;

    public List<EventDto> parseEvent(final Document doc) {
        final List<EventDto> results = CommonUtil.list();

        final List<Element> eventELs = doc.getElementsByClass("unit");

        for (Element eventEL : eventELs) {
            final EventDto event = new EventDto();

            final Element contentEL = eventEL.getElementsByClass("listUnitCap").first();
            event.setContent(contentEL.text());

            final String contentText = contentEL.text().replace("：", ":").replace("時", ":00");

            final Pattern pDatetime = Pattern.compile("([0-9]+)年([0-9]+)月([0-9]+)日[^0-9]*([0-9]+):([0-9]+)[^0-9]*");
            final Matcher mDatetime = pDatetime.matcher(contentText);

            if (mDatetime.find()) {
                final ZonedDateTime date = DateTimeUtil.of(mDatetime.group(1), mDatetime.group(2), mDatetime.group(3),
                        mDatetime.group(4), mDatetime.group(5), "Asia/Tokyo");
                event.setStartDateTime(date.toLocalDateTime());
                event.setEndDateTime(date.plusHours(1).toLocalDateTime());
            } else {
                continue;
            }

            final Element headerEL = eventEL.getElementsByTag("a").first();
            final String name = headerEL.text().replaceAll("【.*?】", "");
            event.setName(name);

            final String headerText = headerEL.text();
            final Pattern pShopName = Pattern.compile("【(.+)】");
            final Matcher mShopName = pShopName.matcher(headerText);
            if (mShopName.find()) {
                final KinokuniyaEnum shop = KinokuniyaEnum.getShopbyName(mShopName.group(1));
                if (shop == null) {
                    continue;
                }
                event.setPlace(shop.getName());
                event.setPrefectureCode(shop.getPrefectureCode());
                event.setStationCode(shop.getStationCode());
            } else {
                continue;
            }

            event.setConditions("");

            final String url = headerEL.attr("href");
            event.setUrl(url);

            final Document info = kinokuniyaClient.getPage(url);
            final Element detailContentEL = info.getElementById("con");
            ;
            final List<String> freeKeywords = KinokuniyaEnum.getKeywordsOfFree();
            final Boolean isFree = freeKeywords.parallelStream().anyMatch(f -> detailContentEL.text().contains(f));
            event.setIsFree(isFree);

            event.setEventCategories(CommonUtil.list(EventCategoryEnum.BOOK));

            final String seed = headerEL.text();
            event.setUuid(UUID.nameUUIDFromBytes(seed.getBytes()).toString());

            EventCategoryDto eventCategoryDto = new EventCategoryDto();
            eventCategoryDto.setId(1);
            event.setEventCategoryDtos(CommonUtil.list(eventCategoryDto));
            
            results.add(event);
        }

        return results;
    }

}
