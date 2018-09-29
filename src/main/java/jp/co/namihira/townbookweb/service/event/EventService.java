package jp.co.namihira.townbookweb.service.event;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ekispert.EkispertClient;
import jp.co.namihira.townbookweb.client.ekispert.Point;
import jp.co.namihira.townbookweb.dao.EventDao;
import jp.co.namihira.townbookweb.dao.StationDao;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.dto.StationDto;
import jp.co.namihira.townbookweb.service.station.StationService;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Service
public class EventService {

	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private StationDao stationDao;
	
	@Autowired
	private EkispertClient ekispertClient;
	
	public EventDto save(final EventDto event) {
		
		final EventDto result = eventDao.save(event);
		
		if (!stationDao.existsByCode(event.getStationCode())) {
			final Point point = ekispertClient.getStation(result.getStationCode()).getPoint();
			final StationDto stationDto = StationService.toStationDto(point);
			stationDao.save(stationDto);
		}
		
		return result;
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
