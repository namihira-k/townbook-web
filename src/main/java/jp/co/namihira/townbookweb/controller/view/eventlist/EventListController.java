package jp.co.namihira.townbookweb.controller.view.eventlist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventListController {
        		
    @GetMapping("/eventlist")
    public String get() {
    	return "eventlist/body";
    }

}
