package jp.co.namihira.townbookweb.client.ekispert;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EkispertListResponse {

	@JsonProperty("ResultSet")
	private ResultListSet resultListSet;
	
}
