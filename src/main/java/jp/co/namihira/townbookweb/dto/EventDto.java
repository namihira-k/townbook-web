package jp.co.namihira.townbookweb.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventDto {

	public String name;
	
	@JsonProperty("date")
	public LocalDate dateTime;
	
	@JsonProperty("start_time")
	public String startTime;

	@JsonProperty("end_time")
	public String endTime;

	
}
