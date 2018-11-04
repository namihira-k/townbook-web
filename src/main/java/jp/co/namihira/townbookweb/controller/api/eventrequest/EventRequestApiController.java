package jp.co.namihira.townbookweb.controller.api.eventrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.controller.api.AbstractApiController;
import jp.co.namihira.townbookweb.dto.EventRequestDto;
import jp.co.namihira.townbookweb.service.eventrequest.EventRequestService;
import jp.co.namihira.townbookweb.service.twitter.TwitterService;

@RestController
public class EventRequestApiController extends AbstractApiController {

	private static final String BASE_PATH = "/eventrequests";

	@Autowired
	private EventRequestService eventRequestService;
	
	@Autowired
	private TwitterService twitterService;
	
	@PostMapping(BASE_PATH)
	@ResponseStatus(HttpStatus.CREATED)
	public EventRequestDto post(@RequestBody EventRequestDto dto) {
		twitterService.postDM("yorimichi_jp", "posted event request! : " + dto.getName() + ", " + dto.getUrl());
		return eventRequestService.save(dto);
    }
	
}
