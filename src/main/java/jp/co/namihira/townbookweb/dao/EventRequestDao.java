package jp.co.namihira.townbookweb.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jp.co.namihira.townbookweb.dto.EventRequestDto;

@Repository
public interface EventRequestDao extends PagingAndSortingRepository<EventRequestDto, Integer>  {

	
}
