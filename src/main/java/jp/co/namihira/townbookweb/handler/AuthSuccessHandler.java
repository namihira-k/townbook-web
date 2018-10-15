package jp.co.namihira.townbookweb.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jp.co.namihira.townbookweb.service.UrlService;

@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Autowired
	private UrlService urlService;
    
    @Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
        urlService.sendRedirect(request, response, "/view/welcome");    	
    }
    
}