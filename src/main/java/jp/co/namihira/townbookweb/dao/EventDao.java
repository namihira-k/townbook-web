package jp.co.namihira.townbookweb.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.namihira.townbookweb.dto.EventDto;

@Repository
public interface EventDao extends CrudRepository<EventDto, Integer>  {

	public List<EventDto> findByStationCodeOrderByStartDateTimeAsc(String stationCode);
	
	public List<EventDto> findByStartDateTimeAfterOrderByStartDateTimeAsc(LocalDateTime startDateTime);
	
	public List<EventDto> findByPrefectureCodeAndStartDateTimeAfterOrderByStartDateTimeAsc(String prefectureCode, LocalDateTime startDateTime);
	
	public List<EventDto> findByStationCodeAndStartDateTimeAfterOrderByStartDateTimeAsc(String stationCode, LocalDateTime startDateTime);
	
}
