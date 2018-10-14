package jp.co.namihira.townbookweb.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.dao.UserDao;
import jp.co.namihira.townbookweb.dto.UserDto;
import jp.co.namihira.townbookweb.service.SecurityService;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SecurityService securityService;
	
	public UserDto save(final UserDto userDto) {
		final String password = userDto.getPassword();
		if (password != null) {
			userDto.setPassword(securityService.encrypt(password));			
		}
		return userDao.save(userDto);
	}
	
}
