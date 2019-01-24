package jp.co.namihira.townbookweb.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiTestController extends AbstractApiController {
       
    @RequestMapping("/apitest")
    public String get() {
        return "Greetings from Spring Boot!";
    }
    
}
