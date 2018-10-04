package jp.co.namihira.townbookweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.dao.UserDao;
import jp.co.namihira.townbookweb.dto.UserDto;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
		
	@Override	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {	
		if (username == null || "".equals(username)) {
			throw new UsernameNotFoundException("Username is empty");
	    }
	    
		final UserDto userDto = userDao.findByUserId(username);
		if (userDto == null) {
			throw new UsernameNotFoundException("User not found for name: " + username);
		}
		return userDto;
	  }

}
