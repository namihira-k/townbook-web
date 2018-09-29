package jp.co.namihira.townbookweb.controller.view.useradd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.namihira.townbookweb.controller.view.AbstractViewController;

@Controller
public class UserAddController extends AbstractViewController {
        
    @GetMapping("/useradd")
    public String get() {
        return "useradd/body";
    }
    
}
