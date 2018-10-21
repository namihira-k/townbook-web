package jp.co.namihira.townbookweb.controller.api.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.controller.api.AbstractApiController;
import jp.co.namihira.townbookweb.controller.api.AppApiListResponse;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.dto.StationDto;
import jp.co.namihira.townbookweb.service.event.EventService;
import jp.co.namihira.townbookweb.service.station.StationService;

@RestController
public class EventApiController extends AbstractApiController {

	private static final String BASE_PATH = "/events";
	
	@Autowired
	private EventService eventService;
		
	@Autowired
	private StationService stationService;
	
	@PostMapping(BASE_PATH)
	@ResponseStatus(HttpStatus.CREATED)
	public EventDto post(@RequestBody EventDto eventDto) {		
        return eventService.save(eventDto);
    }
	
	@GetMapping(BASE_PATH)
	public AppApiListResponse getList(
			@RequestParam(defaultValue = "") String prefectureCode,			
			@RequestParam(defaultValue = "") String stationCode,
			@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fromDate) {
		if (fromDate == null) {
			fromDate = LocalDate.now();
		}
		final LocalDateTime from = LocalDateTime.of(fromDate, LocalTime.MIDNIGHT);		
		
		final List<EventDto> events = eventService.getEventList(prefectureCode, stationCode, from);

		final List<String> codes = events.stream()
				                         .map(e -> e.getStationCode())
				                         .collect(Collectors.toList());		
		final List<StationDto> stations = stationService.getStationsbyCode(codes);
		events.stream().forEach(e -> {
			Optional<StationDto> dto = StationService.getByCode(e.getStationCode(), stations);
			dto.ifPresent(d -> e.setStationName(d.getName()));
		});
		
		return new AppApiListResponse(events);
	};
	

}
