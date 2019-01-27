package jp.co.namihira.townbookweb.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.dto.PrefectureDto;
import jp.co.namihira.townbookweb.dto.StationStatsDto;
import jp.co.namihira.townbookweb.service.event.EventSearchCondition;
import jp.co.namihira.townbookweb.util.CommonUtil;

@Repository
public interface EventDao extends PagingAndSortingRepository<EventDto, Integer>  {

	public long countByStartDateTimeAfter(LocalDateTime startDateTime);

	public EventDto findByUuid(String uuid);

	public Page<EventDto> findByStartDateTimeAfter(LocalDateTime startDateTime, Pageable page);

	public Page<EventDto> findByPrefectureCodeAndStartDateTimeAfter(String prefectureCode, LocalDateTime startDateTime, Pageable page);

	public Page<EventDto> findByStationCodeAndStartDateTimeAfter(String stationCode, LocalDateTime startDateTime, Pageable page);	

    public default Page<EventDto> findByCondition(EventSearchCondition condition, Pageable page) {
        if (CommonUtil.isNotEmpty(condition.getStationCodes())) {
            return findByStationCodeAndStartDateTimeAfterAndCategory(condition, page);
        }

        if (CommonUtil.isNotEmpty(condition.getPrefectureCodes())) {
            return findByPrefectureCodeAndStartDateTimeAfterAndCategory(condition, page);
        }
        
        return findByStartDateTimeAfter(condition.getStartDateTime(), page);
    }

    @Query(value = 
        "SELECT e " +
        "FROM EventDto e " +
        "JOIN e.eventCategoryDtos ec " +
        "WHERE 1 = 1 " + 
        "  AND e.prefectureCode IN ( :#{#conditions.prefectureCodes} ) " +
        "  AND :#{#conditions.startDateTime} <= e.startDateTime " +
        "  AND ec.eventCategory IN ( :#{#conditions.categpries} )"
    )
    public Page<EventDto> findByPrefectureCodeAndStartDateTimeAfterAndCategory(@Param("conditions") EventSearchCondition condition, Pageable page);

    @Query(value = 
        "SELECT e " +
        "FROM EventDto e " +
        "JOIN e.eventCategoryDtos ec " +
        "WHERE 1 = 1 " + 
        "  AND e.stationCode IN ( :#{#conditions.stationCodes} ) " +
        "  AND :#{#conditions.startDateTime} <= e.startDateTime " +
        "  AND ec.eventCategory IN ( :#{#conditions.categpries} )"
    )    
    public Page<EventDto> findByStationCodeAndStartDateTimeAfterAndCategory(@Param("conditions") EventSearchCondition condition, Pageable page);
    
	@Query(value = 
		"SELECT new jp.co.namihira.townbookweb.dto.StationStatsDto(e.stationCode, COUNT(e)) " +
		"FROM EventDto e " +
		"WHERE 1 = 1 " +
		"  AND :from <= e.startDateTime " +
		"  AND e.startDateTime <= :to " +
		"GROUP BY " +
		"  e.stationCode "
	)
	public List<StationStatsDto> countByStartDateTimeGroupByStationCode(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);	

	
	@Query(value = 
		"SELECT new jp.co.namihira.townbookweb.dto.PrefectureDto(d.prefectureCode) " +
		"FROM EventDto d " +
		"WHERE 1 = 1 " +
		"  AND :from <= d.startDateTime " +				
		"GROUP BY " +
		"  d.prefectureCode "
	)
	public List<PrefectureDto> findPrefectures(@Param("from") LocalDateTime from);

	
}
