package jp.co.namihira.townbookweb.controller.view.eventadd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventAddController {
        
    @GetMapping("/eventadd")
    public String get() {
        return "eventadd/body";
    }
    
}
