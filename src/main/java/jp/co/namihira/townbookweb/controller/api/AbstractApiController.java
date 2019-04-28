package jp.co.namihira.townbookweb.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.namihira.townbookweb.service.twitter.TwitterService;

@Controller
@RequestMapping("/api")
public class AbstractApiController {
    
    @Autowired
    protected TwitterService twitterService;
    
}
