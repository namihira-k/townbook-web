package jp.co.namihira.townbookweb.controller.api.eventsearch;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.consts.EventTypeEnum;
import jp.co.namihira.townbookweb.controller.api.AbstractApiController;
import jp.co.namihira.townbookweb.controller.api.AppApiListResponse;
import jp.co.namihira.townbookweb.controller.view.eventinfo.EventInfoController;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.dto.StationDto;
import jp.co.namihira.townbookweb.service.UrlService;
import jp.co.namihira.townbookweb.service.event.EventSearchCondition;
import jp.co.namihira.townbookweb.service.event.EventService;
import jp.co.namihira.townbookweb.service.station.StationService;

@RestController
public class EventSearchApiController extends AbstractApiController {

    private static final String BASE_PATH = "/eventsearch";

    @Autowired
    private EventService eventService;

    @Autowired
    private StationService stationService;
    
    @Autowired
    private UrlService urlService;
        
    
    @GetMapping(BASE_PATH)
    public AppApiListResponse get(
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "4") Integer size,
            @RequestParam(required = false) List<EventTypeEnum> eventTypes
    ) {        
        final PageRequest pageRequest = PageRequest.of(page, size, new Sort(Direction.ASC, "startDateTime"));
        final EventSearchCondition condition = new EventSearchCondition();
        condition.setName(q);
        condition.setStartDateTime(LocalDateTime.now());
        condition.setEventTypes(eventTypes);
        
        Page<EventDto> result = eventService.getEventList(condition, pageRequest);
        
        final List<EventDto> events = result.getContent();

        final List<String> codes = events.stream().map(e -> e.getStationCode()).collect(Collectors.toList());
        final List<StationDto> stations = stationService.getStationsbyCode(codes);
        events.stream().forEach(event -> {
            Optional<StationDto> dto = StationService.getByCode(event.getStationCode(), stations);
            dto.ifPresent(d -> event.setStationName(d.getName()));

            event.setViewUrl(urlService.getContextPath() + "/view" + EventInfoController.path + "?uuid=" + event.getUuid());
        });

        return new AppApiListResponse(result.getTotalElements(), events);

    }

}
