package jp.co.namihira.townbookweb.controller.view.eventlist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.namihira.townbookweb.controller.view.AbstractViewController;

@Controller
public class EventListController extends AbstractViewController {
        		
    @GetMapping("/eventlist")
    public String get(
    		Model model,
			@RequestParam(defaultValue = "13") String prefectureCode,
			@RequestParam(defaultValue = "") String lineCode,
			@RequestParam(defaultValue = "") String stationCode
    		) {
    	model.addAttribute("prefectureCode", prefectureCode);
    	model.addAttribute("lineCode", lineCode);
    	model.addAttribute("stationCode", stationCode);
    	return "eventlist/body";
    }

}
