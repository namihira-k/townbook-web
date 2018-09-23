package jp.co.namihira.townbookweb.controller.api.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.service.station.StationServive;

@RestController
@RequestMapping("api/events") 
public class EventApiController {

	@Autowired
	private StationServive stationService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public EventDto post(@RequestBody EventDto eventDto) {
		System.out.println(stationService.getStations());
		
        return new EventDto();
    }

}
