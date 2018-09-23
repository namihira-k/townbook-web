package jp.co.namihira.townbookweb.controller.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppApiListResponse {

	private List<?> results;
	
}
