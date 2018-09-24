package jp.co.namihira.townbookweb.controller.api;

import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.dao.EventDao;
import jp.co.namihira.townbookweb.dto.EventDto;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
	@Autowired
	private EventDao eventDao;
	
	
    @RequestMapping("/hello")
    public EventDto index() {
    	
    	EventDto entity = new EventDto();
    	entity.setStartDateTime(LocalDateTime.now());
    	
    	eventDao.save(entity);
    	
    	return eventDao.findById(1).get();
    	
//        return "Greetings from Spring Boot!";
    }
        
}
