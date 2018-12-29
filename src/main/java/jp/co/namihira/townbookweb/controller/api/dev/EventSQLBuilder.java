package jp.co.namihira.townbookweb.controller.api.dev;

import java.text.MessageFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jp.co.namihira.townbookweb.dto.EventDto;

public class EventSQLBuilder {

	public static String build(final int startId, final List<EventDto> events) {
		String result = "";
		int id = startId;
		
		for (EventDto event : events) {
			String str = String.valueOf(id) + ",";
			
			str += toValueOnSQL(event.getName());
			
			str += toValueOnSQL(event.getPlace());
			str += toValueOnSQL(event.getPrefectureCode());
			str += toValueOnSQL(event.getStationCode());

			str += toValueOnSQL(event.getStartDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00")));
			str += toValueOnSQL(event.getEndDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00")));

			str += toValueOnSQL(event.getConditions());

			str += toValueOnSQL(event.getUrl());

			str += toValueOnSQL(event.getContent());
			
			str += String.valueOf(event.getIsFree()) + ",";
			
			str += toValueOnSQL(event.getUuid());
			
			result += "(" + str.substring(0, str.length()-2) + "),";
			id++;
		}
		
		return result.substring(0, result.length()-1) + ";";
	}
	
	private static String toValueOnSQL(String str) {
		final MessageFormat mf = new MessageFormat("''{0}'', ");
		str = str.replace("'", "");
		String[] msg = {str};
		return mf.format(msg);
	}

	
}
