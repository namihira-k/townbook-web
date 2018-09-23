package jp.co.namihira.townbookweb.controller.api.station;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.controller.api.AppApiListResponse;
import jp.co.namihira.townbookweb.dto.StationDto;
import jp.co.namihira.townbookweb.service.station.StationService;

@RestController
@RequestMapping("api/stations") 
public class StationApiController {

	@Autowired
	private StationService stationService;
    
	@RequestMapping(method=RequestMethod.GET)
	public AppApiListResponse get(@RequestParam int prefectureCode) {
		final List<StationDto> stationDtos = stationService.getStations(prefectureCode);
        return new AppApiListResponse(stationDtos);
    }

}
