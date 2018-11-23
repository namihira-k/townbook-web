package jp.co.namihira.townbookweb.controller.api.stats;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.controller.api.AbstractApiController;
import jp.co.namihira.townbookweb.controller.api.AppApiListResponse;
import jp.co.namihira.townbookweb.dto.EventStatsDto;
import jp.co.namihira.townbookweb.service.event.EventService;

@RestController
public class EventStatsApiController extends AbstractApiController {

	private static final String BASE_PATH = "/stats/event";
	
	@Autowired
	private EventService eventService;
	
	@GetMapping(BASE_PATH)
	public AppApiListResponse getEventStats(){
		final List<EventStatsDto> eventsToday = eventService.getStats(LocalDateTime.now());
		return new AppApiListResponse(eventsToday.size(), eventsToday);
    }

}
