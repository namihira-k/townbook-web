package jp.co.namihira.townbookweb.client.ekispert;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Point {

	@JsonProperty("Prefecture")
	private Prefecture prefecture;
	
	@JsonProperty("Station")
	private Station station;
	
}
