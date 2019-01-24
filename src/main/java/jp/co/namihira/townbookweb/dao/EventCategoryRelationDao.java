package jp.co.namihira.townbookweb.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.namihira.townbookweb.dto.EventCategoryRelationDto;

@Repository
public interface EventCategoryRelationDao extends CrudRepository<EventCategoryRelationDto, Integer>  {

    public List<EventCategoryRelationDto> findByEventIdIn(final List<Integer> eventIds);
    
}
