package jp.co.namihira.townbookweb.controller.view.eventlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.dto.StationDto;
import jp.co.namihira.townbookweb.service.station.StationServive;

@RestController
public class EventListController {
        
	@Autowired
	private StationServive stationService;
		
    @GetMapping("/eventlist")
    public List<StationDto> get() {
    	List<StationDto> stations = stationService.getStations();
    	return stations;
    	
//        return "eventlist/body";
    }

}
