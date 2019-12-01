package jp.co.namihira.townbookweb.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.namihira.townbookweb.service.UrlService;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private UrlService urlService;
	
    @GetMapping
    public String get() {
    	return "redirect:" + urlService.getBaseUrl() + "view/welcome";
    }
	
}
