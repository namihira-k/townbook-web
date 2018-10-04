package jp.co.namihira.townbookweb.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.namihira.townbookweb.dto.UserDto;

@Repository
public interface UserDao extends CrudRepository<UserDto, Integer>  {
	
	public UserDto findByUserId(String userId);
	
}
