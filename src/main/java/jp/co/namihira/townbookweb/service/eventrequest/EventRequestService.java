package jp.co.namihira.townbookweb.service.eventrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.dao.EventRequestDao;
import jp.co.namihira.townbookweb.dto.EventRequestDto;

@Service
public class EventRequestService {

	@Autowired
	private EventRequestDao eventRequestDao;
	
	public EventRequestDto save(final EventRequestDto dto) {
		return eventRequestDao.save(dto);
	}
	
}
