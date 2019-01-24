package jp.co.namihira.townbookweb.client.fukuyashoten;

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
import jp.co.namihira.townbookweb.consts.EventCategoryEnum;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class FukuyaShotenParser implements ServiceParser {

    private static final Logger logger = LoggerFactory.getLogger(FukuyaShotenParser.class);

    @Override
    public List<EventDto> parseEvent(Document doc) {
        final List<EventDto> results = CommonUtil.list();

        List<Element> events = doc.getElementsByClass("topItem");

        for (Element event : events) {
            final EventDto eventDto = new EventDto();
            String header = event.getElementsByTag("h3").text();

            Pattern pHeader = Pattern.compile("(.+) \\| (.+)");
            Matcher mHeader = pHeader.matcher(header);
            if (mHeader.find()) {
                final String title = mHeader.group(1);
                eventDto.setName(title);

                final String shopName = mHeader.group(2);
                FukuyaShotenEnum shop = FukuyaShotenEnum.getShopbyName(shopName);
                eventDto.setPlace(shop.getName());
                eventDto.setPrefectureCode(shop.getPrefectureCode());
                eventDto.setStationCode(shop.getStationCode());
            }

            String datetimeStr = event.getElementsByClass("timest").get(0).text();
            datetimeStr = datetimeStr.replace("（", "(").replace("）", ")").replace("：", ":").replace(" ", "");
            Pattern pTime = Pattern.compile("([0-9]+年[0-9]+月[0-9]+日?)\\(.+\\)([0-9]+:[0-9]+)[^0-9]*");
            Matcher mTime = pTime.matcher(datetimeStr);

            if (mTime.find()) {
                String date = mTime.group(1);
                String time = mTime.group(2);
                LocalDateTime datetime = LocalDateTime.parse(date + " " + time,
                        DateTimeFormatter.ofPattern("yyyy年[]M月[]d日 []H:[]m"));
                eventDto.setStartDateTime(datetime);
                eventDto.setEndDateTime(datetime.plusHours(1));
            } else {
                logger.warn("datetime not match : " + datetimeStr + "[src]" + header);
                continue;
            }

            eventDto.setConditions("");

            String url = event.getElementsByTag("a").first().attr("href");
            eventDto.setUrl(url);

            eventDto.setContent(header);

            eventDto.setIsFree(false);

            eventDto.setEventCategories(CommonUtil.list(EventCategoryEnum.BOOK));

            String seed = datetimeStr + header;
            eventDto.setUuid(UUID.nameUUIDFromBytes(seed.getBytes()).toString());

            results.add(eventDto);
        }

        return results;
    }

}
