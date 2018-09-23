package jp.co.namihira.townbookweb.client.ekispert;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Station {

	private int code;
	
	@JsonProperty("Name")
	private String name;
	
	
}
