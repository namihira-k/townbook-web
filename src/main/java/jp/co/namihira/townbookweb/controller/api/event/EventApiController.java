package jp.co.namihira.townbookweb.controller.api.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.controller.api.AbstractApiController;
import jp.co.namihira.townbookweb.controller.api.AppApiListResponse;
import jp.co.namihira.townbookweb.controller.view.eventinfo.EventInfoController;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.dto.StationDto;
import jp.co.namihira.townbookweb.service.UrlService;
import jp.co.namihira.townbookweb.service.event.EventService;
import jp.co.namihira.townbookweb.service.station.StationService;

@RestController
public class EventApiController extends AbstractApiController {

	private static final String BASE_PATH = "/events";
	
	@Autowired
	private EventService eventService;
		
	@Autowired
	private StationService stationService;
	
	@Autowired
	private UrlService urlService;
	
	@PostMapping(BASE_PATH)
	@ResponseStatus(HttpStatus.CREATED)
	public EventDto post(@RequestBody EventDto eventDto) {		
        return eventService.save(eventDto);
    }
	
	@GetMapping(BASE_PATH)
	public AppApiListResponse getList(
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "2") Integer size,
			@RequestParam(defaultValue = "") String prefectureCode,
			@RequestParam(defaultValue = "") String stationCode,
			@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fromDate) {
		if (fromDate == null) {
			fromDate = LocalDate.now();
		}
		final LocalDateTime from = LocalDateTime.of(fromDate, LocalTime.MIDNIGHT);
		
		
		final PageRequest pageRequest = PageRequest.of(page, size, new Sort(Direction.ASC, "startDateTime"));
		
		final Page<EventDto> result = eventService.getEventList(prefectureCode, stationCode, from, pageRequest);
				
		final List<EventDto> events = result.getContent();		
		final List<String> codes = events.stream()
				                         .map(e -> e.getStationCode())
				                         .collect(Collectors.toList());
		final List<StationDto> stations = stationService.getStationsbyCode(codes);
		events.stream().forEach(event -> {
			Optional<StationDto> dto = StationService.getByCode(event.getStationCode(), stations);
			dto.ifPresent(d -> event.setStationName(d.getName()));
			
			event.setViewUrl(urlService.getContextPath() + "/view" + EventInfoController.path + "?uuid=" + event.getUuid());
		});
		
		return new AppApiListResponse(result.getTotalElements() ,events);
	};
	
	@GetMapping(BASE_PATH + "/{uuid}")
	public EventDto get(@PathVariable String uuid) {
		final EventDto result = eventService.find(uuid);
		if (result == null) {
			return null;
		}

		final StationDto station = stationService.getStationByCode(result.getStationCode());
		if (station != null) {
			result.setStationName(station.getName());
		}
		return result;
	}

}
