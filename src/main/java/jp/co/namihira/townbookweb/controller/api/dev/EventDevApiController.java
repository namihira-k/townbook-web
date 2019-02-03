package jp.co.namihira.townbookweb.controller.api.dev;

import java.util.List;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.client.fukuyashoten.FukuyaShotenClient;
import jp.co.namihira.townbookweb.client.fukuyashoten.FukuyaShotenParser;
import jp.co.namihira.townbookweb.client.hmv.HMVClient;
import jp.co.namihira.townbookweb.client.hmv.HMVParser;
import jp.co.namihira.townbookweb.client.honto.HontoClient;
import jp.co.namihira.townbookweb.client.honto.HontoParser;
import jp.co.namihira.townbookweb.client.kinokuniya.KinokuniyaClient;
import jp.co.namihira.townbookweb.client.kinokuniya.KinokuniyaParser;
import jp.co.namihira.townbookweb.client.sanseido.SanseidoClient;
import jp.co.namihira.townbookweb.client.sanseido.SanseidoParser;
import jp.co.namihira.townbookweb.client.shosen.ShosenClient;
import jp.co.namihira.townbookweb.client.shosen.ShosenParser;
import jp.co.namihira.townbookweb.client.towerrecords.TowerRecordsClient;
import jp.co.namihira.townbookweb.client.towerrecords.TowerRecordsParser;
import jp.co.namihira.townbookweb.controller.api.AbstractApiController;
import jp.co.namihira.townbookweb.dto.EventDto;

@RestController
public class EventDevApiController extends AbstractApiController {

	@Autowired
	private KinokuniyaClient kinokuniyaClient;
	@Autowired
	private KinokuniyaParser kinokuniyaParser;
	
	@Autowired
	private ShosenClient shosenClient;
	@Autowired
	private ShosenParser shosenParser;
	
	@Autowired
	private FukuyaShotenClient fukuyaShotenClient;
	@Autowired
	private FukuyaShotenParser fukuyaShotenParser;
	
	@Autowired
	private HMVClient hmvClient;
	@Autowired
	private HMVParser hmvParser;
	
	@Autowired
	private TowerRecordsClient towerRecordClient;
	@Autowired
	private TowerRecordsParser towerRecordsParser;

	@Autowired
	private SanseidoClient sanseidoClient;
	@Autowired
	private SanseidoParser sanseidoParser;
	
    @Autowired
    private HontoClient hontoClient;
    @Autowired
    private HontoParser hontoParser;
    
    @GetMapping("/dev/eventfetch-honto")
    public String getHontoData() {
        int id = 100;
        
        final List<Document> docs = hontoClient.getEventPages();
        final List<EventDto> events = hontoParser.parseEvent(docs);
        return EventSQLBuilder.build(id, events);
    };    
    
	@GetMapping("/dev/eventfetch-sanseido")
	public String getSanseidoData() {
		int id = 1100;
		
		final List<Document> docs = sanseidoClient.getEventPages();
		final List<EventDto> events = sanseidoParser.parseEvent(docs);
		return EventSQLBuilder.build(id, events);
	}	
	
	@GetMapping("/dev/eventfetch-kinokuniya")
	public String getkinokuniyaData() {
		int id = 900;
		
		final List<Document> docs = kinokuniyaClient.getEventPages();
		final List<EventDto> events = kinokuniyaParser.parseEvent(docs);
		return EventSQLBuilder.build(id, events);
	}
	
	@GetMapping("/dev/eventfetch-shosen")
	public String getShosenData() {
		int id = 700;
		
		final List<Document> docs = shosenClient.getEventPages();
		final List<EventDto> events = shosenParser.parseEvent(docs);
		return EventSQLBuilder.build(id, events);
	}
	
	@GetMapping("/dev/eventfetch-fs")
	public String getFukuyaShotenData() {
		int id = 500;
		
		final List<Document> docs = fukuyaShotenClient.getEventPages();
		final List<EventDto> events = fukuyaShotenParser.parseEvent(docs);
		return EventSQLBuilder.build(id, events);
	}
	
	@GetMapping("/dev/eventfetch-hmv")
	public String getHMVData() {
		int id = 300;
		final List<Document> docs = hmvClient.getEventPages();
		final List<EventDto> events = hmvParser.parseEvent(docs);
		return EventSQLBuilder.build(id, events);
	}
	
	@GetMapping("/dev/eventfetch-tr")
	public String getDatas() {
		int id = 100;

		final List<Document> docs = towerRecordClient.getEventPages();
		final List<EventDto> events = towerRecordsParser.parseEvent(docs);
		return EventSQLBuilder.build(id, events);
	}

}
