package jp.co.namihira.townbookweb.controller.view.eventsearch;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.namihira.townbookweb.controller.view.AbstractViewController;

@Controller
public class EventSearchController extends AbstractViewController {

    public static final String BASE_PATH = "/eventsearch";

    @GetMapping(BASE_PATH)
    public String get(
        Model model,
        @RequestParam(defaultValue = "") String q
    ) {
        model.addAttribute("q", q);
        return "eventsearch/body";
    }

    
}
