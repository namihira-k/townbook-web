package jp.co.namihira.townbookweb.client.ekispert;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EkispertResponse {

	@JsonProperty("ResultSet")
	private ResultSet resultSet;
	
}
