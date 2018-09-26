package jp.co.namihira.townbookweb.service.event;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.dao.EventDao;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class EventService {

	@Autowired
	private EventDao eventDao;
	
	public EventDto save(final EventDto event) {
		return eventDao.save(event);
	}
	
	public List<EventDto> getEventList(final String stationCode, final LocalDateTime from) {
		if (CommonUtil.isEmpty(stationCode)) {
			final Iterable<EventDto> results = eventDao.findByStartDateTimeAfter(from);
			return CommonUtil.toList(results);
		}
		
		return eventDao.findByStationCodeAndStartDateTimeAfter(stationCode, from);
	}
	
	public List<EventDto> getEventList(final String stationCode) {
		if (CommonUtil.isEmpty(stationCode)) {
			final Iterable<EventDto> results = eventDao.findAll();
			return CommonUtil.toList(results);
		}
		
		return eventDao.findByStationCode(stationCode);
	}
		
}
