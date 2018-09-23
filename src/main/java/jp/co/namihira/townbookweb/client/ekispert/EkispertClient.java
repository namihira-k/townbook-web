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
    
    public ResultSet getStations(final int prefectureCode) {
    	final String url = URL_API + "&prefectureCode={prefectureCode}";
		final EkispertResponse response = restTemplate.getForObject(url, EkispertResponse.class, accessKey, prefectureCode);
		return response.getResultSet();
    }
	
}
