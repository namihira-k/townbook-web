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
		final Point point = ekispertClient.getStation(event.getStationCode()).getPoint();		
		
		event.setPrefectureCode(point.getPrefecture().getCode());
		
		if (!stationDao.existsByCode(event.getStationCode())) {
			final StationDto stationDto = StationService.toStationDto(point);
			stationDao.save(stationDto);
		}
		
		return eventDao.save(event);
	}
	
	public List<EventDto> getEventList(final String prefectureCode, final String stationCode, final LocalDateTime from) {
		if (CommonUtil.isNotEmpty(stationCode)) {
			return eventDao.findByStationCodeAndStartDateTimeAfterOrderByStartDateTimeAsc(stationCode, from);
		}
		
		if (CommonUtil.isNotEmpty(prefectureCode)) {
			return eventDao.findByPrefectureCodeAndStartDateTimeAfterOrderByStartDateTimeAsc(prefectureCode, from);			
		}
		
		final Iterable<EventDto> results = eventDao.findByStartDateTimeAfterOrderByStartDateTimeAsc(from);
		return CommonUtil.toList(results);
	}
	
	public List<EventDto> getEventList(final String stationCode) {
		if (CommonUtil.isEmpty(stationCode)) {
			final Iterable<EventDto> results = eventDao.findAll();
			return CommonUtil.toList(results);
		}
		
		return eventDao.findByStationCodeOrderByStartDateTimeAsc(stationCode);
	}
		
}
