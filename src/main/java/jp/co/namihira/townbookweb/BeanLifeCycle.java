package jp.co.namihira.townbookweb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jp.co.namihira.townbookweb.client.kinokuniya.KinokuniyaClient;
import jp.co.namihira.townbookweb.client.kinokuniya.KinokuniyaParser;
import jp.co.namihira.townbookweb.client.towerrecords.TowerRecordsClient;
import jp.co.namihira.townbookweb.client.towerrecords.TowerRecordsParser;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.service.event.EventService;

@Component
public class BeanLifeCycle {

	private static final Logger logger = LoggerFactory.getLogger(BeanLifeCycle.class);
	
	@Value("${app.data.init}")
	private boolean initData = false;
	
	@Autowired
	private TowerRecordsClient towerRecordsClient;
	@Autowired
	private TowerRecordsParser towerRecordsParser;
	
	@Autowired
	private KinokuniyaClient kinokuniyaClient;
	@Autowired
	private KinokuniyaParser kinokuniyaParser;
	
	@Autowired
	private EventService eventService;
	
	@PostConstruct
    public void initAfterStartup() {
		logger.info("data.init.flag : " + initData);
		if (initData) {			
			List<Document> docs = towerRecordsClient.getEventPages();
			List<EventDto> events = towerRecordsParser.parseEvent(docs);
			eventService.save(events);
			
			docs = kinokuniyaClient.getEventPages();
			events = kinokuniyaParser.parseEvent(docs);
			eventService.save(events);
		}
    }
	
	@PreDestroy
    public void cleanupBeforeExit() {
		logger.info("cleanupBeforeExit");
    }
		
}
