package jp.co.namihira.townbookweb.controller.view.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.namihira.townbookweb.controller.view.AbstractViewController;

@Controller
public class LoginViewController extends AbstractViewController {
        
    @GetMapping("/login")
    public String get() {
        return "login/body";
    }
    
}
