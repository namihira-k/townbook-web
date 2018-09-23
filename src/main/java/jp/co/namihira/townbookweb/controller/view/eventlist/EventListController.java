package jp.co.namihira.townbookweb.controller.view.eventlist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventListController {
        		
    @GetMapping("/eventlist")
    public String get() {
    	return "eventlist/body";
    }

}
