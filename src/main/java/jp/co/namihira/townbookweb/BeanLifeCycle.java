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

import jp.co.namihira.townbookweb.client.ServiceClient;
import jp.co.namihira.townbookweb.client.ServiceParser;
import jp.co.namihira.townbookweb.client.fukuyashoten.FukuyaShotenClient;
import jp.co.namihira.townbookweb.client.fukuyashoten.FukuyaShotenParser;
import jp.co.namihira.townbookweb.client.hmv.HMVClient;
import jp.co.namihira.townbookweb.client.hmv.HMVParser;
import jp.co.namihira.townbookweb.client.kinokuniya.KinokuniyaClient;
import jp.co.namihira.townbookweb.client.kinokuniya.KinokuniyaParser;
import jp.co.namihira.townbookweb.client.shosen.ShosenClient;
import jp.co.namihira.townbookweb.client.shosen.ShosenParser;
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
	private EventService eventService;
	
	@PostConstruct
    public void initAfterStartup() {
		logger.info("data.init.flag : " + initData);
		if (initData) {
			initEventData(towerRecordsClient, towerRecordsParser);
			initEventData(kinokuniyaClient, kinokuniyaParser);
			initEventData(hmvClient, hmvParser);
			initEventData(fukuyaShotenClient, fukuyaShotenParser);
			initEventData(shosenClient, shosenParser);
		}
    }
	
	@PreDestroy
    public void cleanupBeforeExit() {
		logger.info("cleanupBeforeExit");
    }
	
	private void initEventData(ServiceClient client, ServiceParser parser) {
		List<Document> docs = client.getEventPages();
		List<EventDto> events = parser.parseEvent(docs);
		eventService.save(events);		
	}
		
}
