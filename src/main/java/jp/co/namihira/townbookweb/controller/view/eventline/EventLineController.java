package jp.co.namihira.townbookweb.controller.view.eventline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.namihira.townbookweb.controller.view.AbstractViewController;

@Controller
public class EventLineController extends AbstractViewController {
        
    @GetMapping("/eventline")
    public String get() {
        return "eventline/body";
    }
    
}
