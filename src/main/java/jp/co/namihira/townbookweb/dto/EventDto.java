package jp.co.namihira.townbookweb.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventDto {

	public String name;
	
	public LocalDate date;
	
	public String startTime;

	public String endTime;

}
