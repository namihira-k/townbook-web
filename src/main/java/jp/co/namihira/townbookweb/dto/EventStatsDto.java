package jp.co.namihira.townbookweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EventStatsDto {
	
	private String stationCode;
	private Long count;
	
}
