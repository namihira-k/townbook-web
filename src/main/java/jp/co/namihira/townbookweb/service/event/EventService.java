package jp.co.namihira.townbookweb.service.event;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.client.ekispert.EkispertClient;
import jp.co.namihira.townbookweb.client.ekispert.Point;
import jp.co.namihira.townbookweb.consts.EventCategoryEnum;
import jp.co.namihira.townbookweb.dao.EventDao;
import jp.co.namihira.townbookweb.dao.StationDao;
import jp.co.namihira.townbookweb.dto.EventCategoryDto;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.dto.EventStatsDto;
import jp.co.namihira.townbookweb.dto.StationDto;
import jp.co.namihira.townbookweb.dto.StationStatsDto;
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

    public List<EventDto> save(final List<EventDto> events) {
        List<EventDto> results = CommonUtil.toList(eventDao.saveAll(events));
        return results;
    }

    public EventDto save(final EventDto event) {
        final Point point = ekispertClient.getStation(event.getStationCode()).getPoint();

        event.setPrefectureCode(point.getPrefecture().getCode());

        if (!stationDao.existsByCode(event.getStationCode())) {
            final StationDto stationDto = StationService.toStationDto(point);
            stationDao.save(stationDto);
        }

        return eventDao.save(event);
    }

    public EventDto find(final String uuid) {
        final EventDto result = eventDao.findByUuid(uuid);
        return result;
    }

    
    public Page<EventDto> getEventList(final EventSearchCondition condition, final PageRequest pageRequest) {
        return eventDao.findByCondition(condition, pageRequest);
    }
    
    public Page<EventDto> getEventList(final String prefectureCode, final String stationCode, final List<EventCategoryEnum> categpries, final LocalDateTime from,
            final PageRequest pageRequest) {
        if (CommonUtil.isNotEmpty(stationCode)) {
            return eventDao.findByStationCodeAndStartDateTimeAfter(stationCode, from, pageRequest);
        }

        if (CommonUtil.isNotEmpty(prefectureCode)) {
            return eventDao.findByPrefectureCodeAndStartDateTimeAfter(prefectureCode, from, pageRequest);
        }

        return eventDao.findByStartDateTimeAfter(from, pageRequest);
    }

    public List<StationStatsDto> getStationStats(final LocalDateTime from) {
        final LocalDateTime to = from.plusDays(1).withHour(23).withMinute(59);
        return eventDao.countByStartDateTimeGroupByStationCode(from, to);
    }

    public EventStatsDto getEventStats(final LocalDateTime from) {
        final long count = eventDao.countByStartDateTimeAfter(from);
        final EventStatsDto dto = new EventStatsDto();
        dto.setTotalCount(count);
        return dto;
    }

    public List<EventDto> merge(final List<EventDto> eventDtos) {
        eventDtos.forEach(e -> {
            List<EventCategoryDto> categoryDtos = e.getEventCategoryDtos();
            for (EventCategoryDto dto : categoryDtos) {
                e.addEventCategory(dto.getEventCategory());                
            }
        });
        return eventDtos;
    }

}
