package jp.co.namihira.townbookweb.client.ekispert;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResultListSet {

	private String max;
	private String offset;

	@JsonProperty("Point")
	private List<Point> points;
	
}
