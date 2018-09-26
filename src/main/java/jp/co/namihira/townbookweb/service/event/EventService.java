package jp.co.namihira.townbookweb.service.event;

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
	
	public List<EventDto> getEventList() {
		final Iterable<EventDto> results = eventDao.findAll();		
		return CommonUtil.toList(results);
	}
		
}
