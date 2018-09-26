package jp.co.namihira.townbookweb.controller.view.eventadd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.namihira.townbookweb.controller.view.AbstractViewController;

@Controller
public class EventAddController extends AbstractViewController {
        
    @GetMapping("/eventadd")
    public String get() {
        return "eventadd/body";
    }
    
}
