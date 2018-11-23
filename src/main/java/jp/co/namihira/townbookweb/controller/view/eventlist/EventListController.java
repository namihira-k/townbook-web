package jp.co.namihira.townbookweb.controller.view.eventlist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.namihira.townbookweb.controller.view.AbstractViewController;

@Controller
public class EventListController extends AbstractViewController {

	public static final String BASE_PATH = "/eventlist";	
	
    @GetMapping(BASE_PATH)
    public String get(
    		Model model,
			@RequestParam(defaultValue = "13") String prefectureCode,
			@RequestParam(defaultValue = "") String stationCode
    ) {
    	model.addAttribute("prefectureCode", prefectureCode);
    	model.addAttribute("stationCode", stationCode);
    	return "eventlist/body";
    }

}
