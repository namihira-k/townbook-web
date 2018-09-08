package jp.co.namihira.townbookweb.service.station;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jp.co.namihira.townbookweb.dto.StationDto;
import jp.co.namihira.townbookweb.dto.StationLineDto;

@Service
public class StationServive {

	
    private static final String URL = "http://www.ekidata.jp/api/l/{lineCode}.xml";

    private RestTemplate restTemplate = new RestTemplate();
	
	public List<StationDto> getStations() {
		StationLineDto line = restTemplate.getForObject(URL, StationLineDto.class, 11302);
		
		return line.getStation_l();
	}
	
}
