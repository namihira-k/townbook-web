package jp.co.namihira.townbookweb.controller.view.eventlist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.namihira.townbookweb.controller.view.AbstractViewController;

@Controller
public class EventListController extends AbstractViewController {
        		
    @GetMapping("/eventlist")
    public String get() {
    	return "eventlist/body";
    }

}
