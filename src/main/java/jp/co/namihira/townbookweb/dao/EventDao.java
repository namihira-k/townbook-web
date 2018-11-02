package jp.co.namihira.townbookweb.dao;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jp.co.namihira.townbookweb.dto.EventDto;

@Repository
public interface EventDao extends PagingAndSortingRepository<EventDto, Integer>  {

	public EventDto findByUuid(String uuid);
	
	public Page<EventDto> findByStartDateTimeAfter(LocalDateTime startDateTime, Pageable page);
	
	public Page<EventDto> findByPrefectureCodeAndStartDateTimeAfter(String prefectureCode, LocalDateTime startDateTime, Pageable page);
	
	public Page<EventDto> findByStationCodeAndStartDateTimeAfter(String stationCode, LocalDateTime startDateTime, Pageable page);	
	
}
