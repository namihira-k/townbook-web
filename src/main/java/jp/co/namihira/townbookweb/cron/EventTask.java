package jp.co.namihira.townbookweb.cron;

import java.util.List;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jp.co.namihira.townbookweb.client.ServiceClient;
import jp.co.namihira.townbookweb.client.ServiceParser;
import jp.co.namihira.townbookweb.client.fukuyashoten.FukuyaShotenClient;
import jp.co.namihira.townbookweb.client.fukuyashoten.FukuyaShotenEnum;
import jp.co.namihira.townbookweb.client.fukuyashoten.FukuyaShotenParser;
import jp.co.namihira.townbookweb.client.hmv.HMVClient;
import jp.co.namihira.townbookweb.client.hmv.HMVParser;
import jp.co.namihira.townbookweb.client.hmv.HMVShopEnum;
import jp.co.namihira.townbookweb.client.honto.HontoClient;
import jp.co.namihira.townbookweb.client.honto.HontoParser;
import jp.co.namihira.townbookweb.client.honto.JunkudoShopEnum;
import jp.co.namihira.townbookweb.client.honto.MaruzenShopEnum;
import jp.co.namihira.townbookweb.client.kinokuniya.KinokuniyaClient;
import jp.co.namihira.townbookweb.client.kinokuniya.KinokuniyaEnum;
import jp.co.namihira.townbookweb.client.kinokuniya.KinokuniyaParser;
import jp.co.namihira.townbookweb.client.sanseido.SanseidoClient;
import jp.co.namihira.townbookweb.client.sanseido.SanseidoParser;
import jp.co.namihira.townbookweb.client.sanseido.SanseidoShopEnum;
import jp.co.namihira.townbookweb.client.shosen.ShosenClient;
import jp.co.namihira.townbookweb.client.shosen.ShosenEnum;
import jp.co.namihira.townbookweb.client.shosen.ShosenParser;
import jp.co.namihira.townbookweb.client.towerrecords.TowerRecordsClient;
import jp.co.namihira.townbookweb.client.towerrecords.TowerRecordsParser;
import jp.co.namihira.townbookweb.client.towerrecords.TowerRecordsShopEnum;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.service.event.EventService;
import jp.co.namihira.townbookweb.service.twitter.TwitterService;

@Component
public class EventTask {

    private static final Logger logger = LoggerFactory.getLogger(EventTask.class);    
    
    @Autowired
    private TowerRecordsClient towerRecordsClient;
    @Autowired
    private TowerRecordsParser towerRecordsParser;

    @Autowired
    private KinokuniyaClient kinokuniyaClient;
    @Autowired
    private KinokuniyaParser kinokuniyaParser;

    @Autowired
    private HMVClient hmvClient;
    @Autowired
    private HMVParser hmvParser;

    @Autowired
    private FukuyaShotenClient fukuyaShotenClient;
    @Autowired
    private FukuyaShotenParser fukuyaShotenParser;

    @Autowired
    private ShosenClient shosenClient;
    @Autowired
    private ShosenParser shosenParser;

    @Autowired
    private SanseidoClient sanseidoClient;
    @Autowired
    private SanseidoParser sanseidoParser;

    @Autowired
    private HontoClient hontoClent;
    @Autowired
    private HontoParser hontoParser;
    
    @Autowired
    private TwitterService twitterService;

    @Autowired
    private EventService eventService;
    
    @Scheduled(cron = "0 0 21 * * *")
    public void initEventData() {
        final String msg = "initEventData";
        
        logStart(msg);
        
        eventService.deleteByOrgCode(JunkudoShopEnum.ORG_CODE);
        eventService.deleteByOrgCode(MaruzenShopEnum.ORG_CODE);
        initEventData(hontoClent, hontoParser);

        eventService.deleteByOrgCode(SanseidoShopEnum.ORG_CODE);        
        initEventData(sanseidoClient, sanseidoParser);

        eventService.deleteByOrgCode(KinokuniyaEnum.ORG_CODE);        
        initEventData(kinokuniyaClient, kinokuniyaParser);

        eventService.deleteByOrgCode(FukuyaShotenEnum.ORG_CODE);        
        initEventData(fukuyaShotenClient, fukuyaShotenParser);

        eventService.deleteByOrgCode(ShosenEnum.ORG_CODE);        
        initEventData(shosenClient, shosenParser);
        
        logEnd(msg);
    }

    @Scheduled(cron = "0 5 21 * * *")
    public void initTowerRecordsEventData() {
        final String msg = "TowerRecords";
        
        logStart(msg);
        
        eventService.deleteByOrgCode(TowerRecordsShopEnum.ORG_CODE);
        initEventData(towerRecordsClient, towerRecordsParser);
        
        logEnd(msg);
    }
    
    @Scheduled(cron = "0 10 21 * * *")
    public void initHmvEventData() {
        final String msg = "HMV";
        
        logStart(msg);
        
        eventService.deleteByOrgCode(HMVShopEnum.ORG_CODE);
        initEventData(hmvClient, hmvParser);
        
        logEnd(msg);
    }
    
    
    private void initEventData(ServiceClient client, ServiceParser parser) {
        try {
            List<Document> docs = client.getEventPages();
            List<EventDto> events = parser.parseEvent(docs);
            eventService.save(events);
            logger.info("done initEventData : " + events.get(0).getPlace());            
        } catch (RuntimeException e) {
            twitterService.postDMtoAdmin("error initEventData : " + client.getClass().getName());
        }
    }
    
    private void logStart(final String msg) {
        logger.info("cron start : " + msg);
        twitterService.postDMtoAdmin("cron start : " + msg);
    }
    
    private void logEnd(final String msg) {
        logger.info("cron end : " + msg);
        twitterService.postDMtoAdmin("cron end : " + msg);
    }

        
}
