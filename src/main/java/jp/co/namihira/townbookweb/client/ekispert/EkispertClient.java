package jp.co.namihira.townbookweb.client.ekispert;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EkispertClient {

	@Value("${ekispert.accesskey}")
	private String accessKey = "";
	
    private static final String URL_API = "https://api.ekispert.jp/v1/json/station?key={accessKey}";

    private RestTemplate restTemplate = new RestTemplate();
    
    public ResultListSet getStations(final int prefectureCode) {
    	final String url = URL_API + "&prefectureCode={prefectureCode}";
		final EkispertListResponse response = restTemplate.getForObject(url, EkispertListResponse.class, accessKey, prefectureCode);
		return response.getResultListSet();
    }
    
    public ResultSet getStation(final String stationCode) {
    	final String url = URL_API + "&code={stationCode}";
		final EkispertResponse response = restTemplate.getForObject(url, EkispertResponse.class, accessKey, stationCode);
		return response.getResultSet();
	}

}
