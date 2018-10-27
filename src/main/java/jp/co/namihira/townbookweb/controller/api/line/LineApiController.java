package jp.co.namihira.townbookweb.controller.api.line;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.controller.api.AbstractApiController;
import jp.co.namihira.townbookweb.controller.api.AppApiListResponse;
import jp.co.namihira.townbookweb.dto.LineDto;
import jp.co.namihira.townbookweb.service.station.StationService;

@RestController
public class LineApiController extends AbstractApiController {

	private static final String BASE_PATH = "/lines";
	
	@Autowired
	private StationService stationService;
    
	@GetMapping(BASE_PATH)
	public AppApiListResponse get(@RequestParam int prefectureCode) {
		final List<LineDto> dtos = stationService.getLines(prefectureCode);
        return new AppApiListResponse(dtos.size(), dtos);
    }

}
