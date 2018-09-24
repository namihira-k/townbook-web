package jp.co.namihira.townbookweb.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.namihira.townbookweb.dto.EventDto;

@Repository
public interface EventDao extends CrudRepository<EventDto, Integer>  {
	
}
