package jp.co.namihira.townbookweb.controller.view.eventinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.namihira.townbookweb.controller.view.AbstractViewController;
import jp.co.namihira.townbookweb.dto.EventDto;
import jp.co.namihira.townbookweb.service.event.EventService;

@Controller
public class EventInfoController extends AbstractViewController {
    
	public static final String path = "/eventinfo";

	@Autowired
	private EventService eventService;
	
    @GetMapping(path)
    public String get(Model model, @RequestParam(required = false) String uuid) {
    	model.addAttribute("uuid", uuid);
    	
    	// for twitter card
    	final EventDto event = eventService.find(uuid);
    	model.addAttribute("event", event);

        model.addAttribute("prefectureCode", event.getPrefectureCode());
        model.addAttribute("stationCode", event.getStationCode());
        
        return "eventinfo/body";
    }
    
}
