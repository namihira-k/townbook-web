package jp.co.namihira.townbookweb.controller.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.consts.AuthorityEnum;
import jp.co.namihira.townbookweb.controller.api.AbstractApiController;
import jp.co.namihira.townbookweb.dto.UserDto;
import jp.co.namihira.townbookweb.service.user.UserService;

@RestController
public class UserApiController extends AbstractApiController {

	private static final String BASE_PATH = "/users";
	
	@Autowired
	private UserService userService;
	
	@PostMapping(BASE_PATH)
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto post(@RequestBody UserDto userDto) {
		userDto.setAuthority(AuthorityEnum.USER);
		return userService.save(userDto);
    }

}
