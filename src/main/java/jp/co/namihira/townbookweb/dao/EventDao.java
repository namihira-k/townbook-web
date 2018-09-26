package jp.co.namihira.townbookweb.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.namihira.townbookweb.dto.EventDto;

@Repository
public interface EventDao extends CrudRepository<EventDto, Integer>  {

	public List<EventDto> findByStationCode(String stationCode);
	
	public List<EventDto> findByStartDateTimeAfter(LocalDateTime startDateTime);
	
	public List<EventDto> findByStationCodeAndStartDateTimeAfter(String stationCode, LocalDateTime startDateTime);
	
}
