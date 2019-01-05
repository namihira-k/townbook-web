package jp.co.namihira.townbookweb.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.namihira.townbookweb.dto.StationDto;

@Repository
public interface StationDao extends CrudRepository<StationDto, Integer>  {

	public boolean existsByCode(final String code);
	
	public StationDto findByCode(final String code);
	
	public List<StationDto> findByPrefectureCode(final String prefectureCode);
	
	public List<StationDto> findByCodeIn(final List<String> codes);	
	
}
