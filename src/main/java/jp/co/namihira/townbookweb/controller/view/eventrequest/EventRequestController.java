package jp.co.namihira.townbookweb.controller.view.eventrequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.namihira.townbookweb.controller.view.AbstractViewController;

@Controller
public class EventRequestController extends AbstractViewController {
    
	public static final String path = "/eventrequest";
		
    @GetMapping(path)
    public String get() {
        return "eventrequest/body";
    }
    
}
