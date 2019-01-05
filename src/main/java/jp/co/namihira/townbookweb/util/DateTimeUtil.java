package jp.co.namihira.townbookweb.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtil {

	
	public static ZonedDateTime of(String year, String month, String dayOfMonth, String hour, String minute, String zoneId) {
		return ZonedDateTime.of(
				Integer.parseInt(year),
				Integer.parseInt(month),
				Integer.parseInt(dayOfMonth),
				Integer.parseInt(hour),
				Integer.parseInt(minute),		
				0,
				0,
				ZoneId.of(zoneId));
	}

	
}
