package jp.co.namihira.townbookweb.controller.view.eventinfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.namihira.townbookweb.controller.view.AbstractViewController;

@Controller
public class EventInfoController extends AbstractViewController {
        
    @GetMapping("/eventinfo")
    public String get() {
        return "eventinfo/body";
    }
    
}
