package jp.co.namihira.townbookweb.controller.api.station;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.controller.api.AbstractApiController;
import jp.co.namihira.townbookweb.controller.api.AppApiListResponse;
import jp.co.namihira.townbookweb.dto.StationDto;
import jp.co.namihira.townbookweb.service.station.StationService;

@RestController
public class StationApiController extends AbstractApiController {

	private static final String BASE_PATH = "/stations";
	
	@Autowired
	private StationService stationService;
    
	@GetMapping(BASE_PATH)
	public AppApiListResponse get(
			@RequestParam(defaultValue = "") String prefectureCode,
			@RequestParam(defaultValue = "") String lineCode) {
		final List<StationDto> stationDtos = stationService.getStations(prefectureCode, lineCode);
        return new AppApiListResponse(stationDtos);
    }

}
