package jp.co.namihira.townbookweb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.namihira.townbookweb.dto.PrefectureDto;
import jp.co.namihira.townbookweb.dto.StationDto;

@Repository
public interface StationDao extends CrudRepository<StationDto, Integer>  {

	public boolean existsByCode(final String code);
	
	public StationDto findByCode(final String code);
	
	public List<StationDto> findByPrefectureCode(final String prefectureCode);
	
	public List<StationDto> findByCodeIn(final List<String> codes);
	
	
	@Query(value = 
			"SELECT new jp.co.namihira.townbookweb.dto.PrefectureDto(d.prefectureCode) " +
			"FROM StationDto d " +
			"GROUP BY " +
			"  d.prefectureCode "
	)
	public List<PrefectureDto> findPrefectures();
	
	
}
