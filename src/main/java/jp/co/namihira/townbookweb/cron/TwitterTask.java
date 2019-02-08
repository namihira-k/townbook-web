package jp.co.namihira.townbookweb.cron;

import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jp.co.namihira.townbookweb.controller.view.eventinfo.EventInfoController;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.service.UrlService;
import jp.co.namihira.townbookweb.service.event.EventService;
import jp.co.namihira.townbookweb.service.twitter.TwitterService;

@Component
public class TwitterTask {

    private static final Logger logger = LoggerFactory.getLogger(TwitterTask.class);
    
    @Autowired
    private EventService eventService;
    
    @Autowired
    private TwitterService twitterService;
    
    @Autowired
    private UrlService urlService;

    @Scheduled(cron = "0 30 9,12,15,18,21 * * *")
    public void tweetEvent() {
        final EventDto eventDto = eventService.getLatestFreeEvent();
        final StringBuilder sb = new StringBuilder();
        
        final DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        sb.append("【").append(eventDto.getStartDateTime().format(fm)).append("、").append(eventDto.getPlace()).append("】").append("\n")
          .append(eventDto.getName()).append("\n");
        
        eventDto.setViewUrl(urlService.getBaseUrl() + "/view" + EventInfoController.path + "?uuid=" + eventDto.getUuid());
        sb.append(eventDto.getViewUrl()).append("\n");
        
        sb.append("【当日参加可！】【参加無料！】").append("\n");
        
        final String text = new String(sb);
        twitterService.tweet(text);
        twitterService.postDMtoAdmin("Posted : " + text);
        
        logger.info("done tweetEvent");
    }

}
