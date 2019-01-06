package jp.co.namihira.townbookweb.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StationStatsDto {
		
	private String stationCode;
	private Long count;
	
	public String prefectureCode;
	private String stationName;
	private String viewUrl;
	
	public StationStatsDto(final String stationCode, final Long count) {
		this.stationCode = stationCode;
		this.count = count;
	};

}
