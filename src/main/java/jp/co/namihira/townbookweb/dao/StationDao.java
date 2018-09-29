package jp.co.namihira.townbookweb.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.namihira.townbookweb.dto.StationDto;

@Repository
public interface StationDao extends CrudRepository<StationDto, Integer>  {

	public boolean existsByCode(final String code);
	
}
