package jp.co.namihira.townbookweb.controller.api.stats;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.controller.api.AbstractApiController;
import jp.co.namihira.townbookweb.controller.api.AppApiListResponse;
import jp.co.namihira.townbookweb.controller.view.eventlist.EventListController;
import jp.co.namihira.townbookweb.dto.EventStatsDto;
import jp.co.namihira.townbookweb.dto.StationDto;
import jp.co.namihira.townbookweb.service.UrlService;
import jp.co.namihira.townbookweb.service.event.EventService;
import jp.co.namihira.townbookweb.service.station.StationService;

@RestController
public class StatsApiController extends AbstractApiController {

	private static final String BASE_PATH = "/stats";
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private StationService stationService;
	
	@Autowired
	private UrlService urlService;
	
	@GetMapping(BASE_PATH + "/station")
	public AppApiListResponse getStationStats(){
		final List<EventStatsDto> events = eventService.getStats(LocalDateTime.now());
		
		final List<String> codes = events.stream()
                					     .map(e -> e.getStationCode())
                                         .collect(Collectors.toList());
		
		final List<StationDto> stations = stationService.getStationsbyCode(codes);
		events.stream().forEach(event -> {
									Optional<StationDto> dto = StationService.getByCode(event.getStationCode(), stations);
									dto.ifPresent(d -> {
										event.setStationName(d.getName());
										event.setPrefectureCode(d.getPrefectureCode());
									});
									event.setViewUrl(urlService.getContextPath() + "/view" + EventListController.BASE_PATH + 
											"?prefectureCode=" + event.getPrefectureCode() + "&stationCode=" + event.getStationCode());
		});
		return new AppApiListResponse(events.size(), events);
    }

}
