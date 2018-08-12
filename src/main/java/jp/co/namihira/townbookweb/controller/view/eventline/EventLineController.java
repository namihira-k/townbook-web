package jp.co.namihira.townbookweb.controller.view.eventline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventLineController {
        
    @GetMapping("/eventline")
    public String get() {
        return "eventline/body";
    }
    
}
