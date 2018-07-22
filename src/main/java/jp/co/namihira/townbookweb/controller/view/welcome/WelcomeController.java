package jp.co.namihira.townbookweb.controller.view.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
        
    @GetMapping("/welcome")
    public String get() {
        return "welcome/body";
    }    
    
}
