package jp.co.namihira.townbookweb.controller.view.eventinfo;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.namihira.townbookweb.controller.view.AbstractViewController;

@Controller
public class EventInfoController extends AbstractViewController {
        
    @GetMapping("/eventinfo")
    public String get(Model model, @RequestParam UUID uuid) {
    	model.addAttribute("uuid", uuid);
        return "eventinfo/body";
    }
    
}
