package jp.co.namihira.townbookweb.controller.view.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.namihira.townbookweb.controller.view.AbstractViewController;

@Controller
public class WelcomeController extends AbstractViewController {
        
    @GetMapping("/welcome")
    public String get() {
        return "welcome/body";
    }    
    
}
